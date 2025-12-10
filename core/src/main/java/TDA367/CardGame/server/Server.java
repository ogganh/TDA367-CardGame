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

    public void send(int targetPlayer, String message) {
        clients.get(targetPlayer).out.println(String.format("Player %d: %s", targetPlayer, message));
    }

    // Method for sending message to player (client)
    public void broadcast(int playerIndex, String message) {
        for (ClientConnection client : clients) {
            if (clients.indexOf(client) == playerIndex)
                continue;
            client.out.println(String.format("Player %d: %s", playerIndex, message));
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
        try {
            while (true) {
                for (ClientConnection client : clients) {
                    if (client.in.ready()) {
                        String msg = client.in.readLine();
                        Gdx.app.log("Server",
                                String.format("Received from client %d: %d", clients.indexOf(client), msg));
                        broadcast(clients.indexOf(client), msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
