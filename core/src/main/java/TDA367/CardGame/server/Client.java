package TDA367.CardGame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;

import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

public class Client {
    private Socket clientSocket;
    private Thread serverListener;
    private Thread writerThread;
    private final ConcurrentLinkedQueue<Object> incoming = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Object> outgoing = new ConcurrentLinkedQueue<>();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private GameController controller;

    public Client(GameController controller, String ip) {
        this.controller = controller;
        this.startConnection(ip, 6666);
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
                        Gdx.app.log("Client", "Message with code: " + serverMessage.messageCode);
                        if (serverMessage.messageCode == 0) {
                            if (String.class.cast(serverMessage.content).equals("setupGame")) {
                                Gdx.app.log("Client", "Setting up game...");
                                Gdx.app.postRunnable(() -> {
                                    controller.setupGame();
                                });
                            }
                        } else if (serverMessage.messageCode == 1) {
                            controller.setState((GameState) serverMessage.content);
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
                            out.writeObject(new ServerMessage(1, controller.getState()));
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