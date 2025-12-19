package TDA367.CardGame.controller.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;

import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.PlayerAction;

public class Client {
    private Socket clientSocket;
    private Thread serverListener;
    private Thread writerThread;
    private final ConcurrentLinkedQueue<Object> incoming = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Object> outgoing = new ConcurrentLinkedQueue<>();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private GameController controller;
    private int playerID;

    public Client(GameController controller, String ip, int port) {
        this.controller = controller;
        this.startConnection(ip, port);
        Gdx.app.log("Client", "Joined server, starting listen...");
        // Threads are started by startConnection()
    }

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            // Start background listener thread to receive messages from server
            serverListener = new Thread(() -> {
                try {
                    this.out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.flush();
                    this.in = new ObjectInputStream(clientSocket.getInputStream());
                    ServerMessage serverMessage;
                    while (!Thread.currentThread().isInterrupted()
                            && (serverMessage = ServerMessage.class.cast(in.readObject())) != null) {
                        // enqueue for main-thread processing
                        final ServerMessage finalServerMessage = serverMessage;
                        Gdx.app.log("Client", "Message with code: " + finalServerMessage.messageCode);
                        if (finalServerMessage.messageCode == 0) {
                            // Ensure UI and GL resources are created on the LibGDX main/render thread
                            Gdx.app.postRunnable(() -> controller.StartGofish());
                        } else if (finalServerMessage.messageCode == 1) {
                            // Post actions to main thread so controller/view operations that may touch GL
                            // or UI state run on the render thread.
                            Gdx.app.postRunnable(() -> {
                                PlayerAction action = (PlayerAction) finalServerMessage.content;
                                controller.handleAction(action.getPlayerIndex(), action.getActionType(),
                                        action.getRank(),
                                        action.getSuit());
                            });
                        } else if (finalServerMessage.messageCode == 2) {
                            playerID = (Integer) finalServerMessage.content;
                        }
                    }
                } catch (Exception e) {
                    Gdx.app.error("Client", "Listener stopped", e);
                }
            }, "Client-Listener");
            serverListener.setDaemon(true);
            serverListener.start();

            // Start writer thread that drains outgoing queue and writes to the stream
            writerThread = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Object o = outgoing.poll();
                        if (o == null) {
                            Thread.sleep(10);
                            continue;
                        }
                        synchronized (out) {
                            out.writeObject(new ServerMessage(1, (Serializable) o));
                            out.flush();
                            out.reset();
                        }
                    }
                } catch (InterruptedException ie) {
                    // thread interrupted — normal on shutdown
                } catch (Exception e) {
                    Gdx.app.error("Client", "Writer stopped", e);
                }
            }, "Client-Writer");
            writerThread.setDaemon(true);
            writerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
        try {
            if (in != null)
                in.close();
        } catch (IOException ignored) {
        }
        try {
            if (out != null)
                out.close();
        } catch (IOException ignored) {
        }
        try {
            if (clientSocket != null)
                clientSocket.close();
        } catch (IOException ignored) {
        }
    }

    public synchronized void send(Object o) {
        outgoing.add(o);
    }

    // Inputs game State update from the client
    public void gameStateInput() {

    }
}