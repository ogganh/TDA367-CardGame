package TDA367.CardGame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * An object used to manage clients connection by the server and their
 * input/output.
 */
public class ClientConnection {
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    ClientConnection(Socket socket) {
        try {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connections and releases the related resources.
     */
    public void close() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
