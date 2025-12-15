package TDA367.CardGame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;

import TDA367.CardGame.View.Views.Games.GoFish;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.player.UserPlayer;

/**
 * The server resposible for sending game state data between players (clients)
 */

public class Server {
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<ClientConnection> clients = new CopyOnWriteArrayList<ClientConnection>();
    private GameController controller;
    private GameState state;

    public void start(GameController controller, int port) {
        this.controller = controller;
        this.state = controller.getState();
        Server server = new Server();
        try {
            serverSocket = new ServerSocket(port);
            Gdx.app.log("Server", "Server started, waiting for clients to connect...");
            handleConnections();
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

    public synchronized void send(ClientConnection client, ServerMessage msg) {
        try {
            if (client.out == null)
                return;
            if (!(msg.content == null || msg.content instanceof java.io.Serializable)) {
                Gdx.app.error("Server", "Attempt to send non-serializable payload: " + msg.content.getClass());
                return;
            }
            client.out.writeObject(msg);
            client.out.flush();
            client.out.reset(); // avoid retaining object graph between writes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for sending message to player (client)
    public void broadcast(ClientConnection sender, ServerMessage msg) {
        for (ClientConnection client : clients) {
            if (client == sender)
                continue;
            // send a copy / the message to each client
            synchronized (client) {
                send(client, msg);
            }
        }
    }

    public void handleConnections() {
        Thread joinManager = new Thread(() -> {
            while (true) {
                try {
                    ClientConnection client = new ClientConnection(serverSocket.accept());
                    clients.add(client);
                    state.addPlayer(new GoFishUserPlayer("name"));
                    Gdx.app.log("Server", "A client joined");
                    Gdx.app.log("Server", String.valueOf(state.getPlayers()));
                    if (clients.size() >= 2) {
                        broadcast(null, new ServerMessage(1, state));
                        broadcast(null, new ServerMessage(0, "setupGame"));
                    }

                    // Start a dedicated listener thread for this client so one
                    // misbehaving client doesn't block handling of others.
                    Thread clientReader = new Thread(() -> {
                        try {
                            while (true) {
                                Object o = client.in.readObject();
                                if (o == null)
                                    break;
                                if (!(o instanceof java.io.Serializable)) {
                                    Gdx.app.error("Server",
                                            "Received non-serializable object from client: " + o.getClass());
                                    continue;
                                }
                                ServerMessage msg = ServerMessage.class.cast(o);
                                Gdx.app.log("Server",
                                        String.format("Received from client %d", clients.indexOf(client)));
                                if (msg.messageCode == 1) {
                                    state = GameState.class.cast(msg.content);
                                    Gdx.app.log("Server", String.valueOf(state.getPlayers().size()));
                                }
                                broadcast(client, msg);
                            }
                        } catch (java.io.EOFException eof) {
                            // Client closed the connection gracefully
                            Gdx.app.log("Server", "Client disconnected");
                        } catch (Exception e) {
                            Gdx.app.error("Server", "Error reading from client", e);
                        } finally {
                            try {
                                clients.remove(client);
                                client.close();
                            } catch (Exception ignore) {
                            }
                        }
                    }, "Client-Reader-" + clients.size());
                    clientReader.setDaemon(true);
                    clientReader.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        joinManager.setDaemon(true);
        joinManager.start();
        Gdx.app.log("Server", "Join thread started");
    }

    public CopyOnWriteArrayList<ClientConnection> getClients() {
        return clients;
    }
}
