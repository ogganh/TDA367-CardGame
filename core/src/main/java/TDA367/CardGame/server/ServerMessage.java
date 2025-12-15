package TDA367.CardGame.server;

import java.io.Serializable;

/**
 * Simple wrapper for messages sent between server and clients.
 * Note: payload (content) must itself be Serializable. Prefer using
 * lightweight DTOs for network transfer instead of sending complex
 * domain objects directly.
 */
public class ServerMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    public int messageCode;
    public Serializable content;

    public ServerMessage(int messageCode, Serializable content) {
        this.messageCode = messageCode;
        this.content = content;
    }
}
