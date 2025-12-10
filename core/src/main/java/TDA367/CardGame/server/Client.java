package TDA367.CardGame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {

        // Using thread to run concurrently with scanner
        // Receiving game state updates from server
        Thread serverListener = new Thread(() -> {
            try {
                String serverMsg;
                while ((serverMsg = in.readLine()) != null) {

                    System.out.println("Server: " + serverMsg);
                    // Fill out with commands to state

                }
            } catch (IOException e) {
                // Stream closed or error
            }
        });
        serverListener.setDaemon(true);
        serverListener.start();

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