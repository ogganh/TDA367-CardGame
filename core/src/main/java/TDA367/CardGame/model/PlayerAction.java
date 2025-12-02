package TDA367.CardGame.model;

public class PlayerAction {
    private int playerIndex;
    private String actionType;
    private String rank;
    private String suit;

    public PlayerAction(int playerIndex, String actionType, String rank, String suit) {
        this.playerIndex = playerIndex;
        this.actionType = actionType;
        this.rank = rank;
        this.suit = suit;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
    public String getActionType() {
        return actionType;
    }
    public String getRank() {
        return rank;
    }
    public String getSuit() {
        return suit;
    }
}
