package TDA367.CardGame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;

/**
 * The server resposible for sending game state data between players (clients)
 */

public class Server {
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<ClientConnection> clients = new CopyOnWriteArrayList<ClientConnection>();

    public void start(int port) {
        Server server = new Server();
        server.start(6666);
        try {
            serverSocket = new ServerSocket(port);
            Gdx.app.log("Server", "Server started, waiting for clients to connect...");

            handleConnections();

            handleMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            for (ClientConnection client : clients) {
                client.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void send(ClientConnection client, Object o) {
        try {
            if (client.out == null)
                return;
            client.out.writeObject(o);
            client.out.flush();
            client.out.reset(); // avoid retaining object graph between writes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for sending message to player (client)
    public void broadcast(ClientConnection sender, Object o) {
        for (ClientConnection client : clients) {
            if (client == sender)
                continue;
            // send a copy / the message to each client
            synchronized (client) {
                send(client, o);
            }
        }
    }

    public void handleConnections() {
        Thread joinManager = new Thread(() -> {
            while (true) {
                try {
                    clients.add(new ClientConnection(serverSocket.accept()));
                    System.out.println("A client joined");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        joinManager.setDaemon(true);
        joinManager.start();
        Gdx.app.log("Server", "Join thread started");
    }

    public void handleMessages() {
        Thread handler = new Thread(() -> {
            while (true) {
                try {
                    Object o;
                    for (ClientConnection client : clients) {
                        while ((o = client.in.readObject()) != null) {
                            o = client.in.readObject();
                            Gdx.app.log("Server",
                                    String.format("Received from client %d", clients.indexOf(client)));
                            broadcast(client, o);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        handler.setDaemon(true);
        handler.start();
    }
}
