package TDA367.CardGame.model.gameLogic.strategies;

/**
 * Manages the turn order for players in a card game based on the number of players.
 */

public class TurnManager {
    private int current_p_index = 0;
    private final int playerCount;

    public TurnManager(int playerCount){
        if (playerCount < 2){
            throw new IllegalArgumentException("TurnManger requires at least 2 players");
        }
        this.playerCount = playerCount;
    }

    public int GetCurrentIndex(){
        return current_p_index;
    }

    public void next(){
        current_p_index = (current_p_index + 1) % playerCount;
    }

}