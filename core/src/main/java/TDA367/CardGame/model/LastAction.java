package TDA367.CardGame.model;

import java.io.Serializable;

public class LastAction implements Serializable {
    public enum SourceType {
        POND, OPPONENT, DEAL, UNKNOWN
    }

    public SourceType source = SourceType.UNKNOWN;
    public int sourcePlayerIndex = -1; // valid if source == OPPONENT
    public int targetPlayerIndex = -1; // who received the card
    public int cardInt = -1; // optional: the card id if available

    public LastAction() {
    }
}