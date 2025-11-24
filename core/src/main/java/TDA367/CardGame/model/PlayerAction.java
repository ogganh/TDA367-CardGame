package TDA367.CardGame.model;
public class PlayerAction {
    private String playerIndex;
    private String actionType;

    public PlayerAction(String playerIndex, String actionType) {
        this.playerIndex = playerIndex;
        this.actionType = actionType;
    }

    public String getPlayerIndex() {
        return playerIndex;
    }
    public String getActionType() {
        return actionType;
    }
}
