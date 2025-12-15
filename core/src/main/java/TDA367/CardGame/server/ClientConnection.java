package TDA367.CardGame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * An object used to manage clients connection by the server and their
 * input/output.
 */
public class ClientConnection {
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    ClientConnection(Socket socket) {
        try {
            this.socket = socket;
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connections and releases the related resources.
     */
    public void close() {
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
            if (socket != null)
                socket.close();
        } catch (IOException ignored) {
        }
    }
}
