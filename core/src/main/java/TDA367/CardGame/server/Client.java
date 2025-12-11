package TDA367.CardGame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            out.flush();
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

    public void listen() {
        // Using thread to run concurrently with scanner
        // Receiving game state updates from server
        Thread serverListener = new Thread(() -> {
            try {
                Object serverObject;
                while ((serverObject = in.readObject()) != null) {
                    System.out.println("Server: " + serverObject);
                    // Fill out with commands to state
                }
            } catch (Exception e) {
                // Stream closed or error
            }
        });
        serverListener.setDaemon(true);
        serverListener.start();
    }

    public synchronized void send(Object o) {
        try {
            if (out == null)
                return;
            out.writeObject(o);
            out.flush();
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inputs game State update from the client
    public void gameStateInput() {

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        client.listen();
    }
}