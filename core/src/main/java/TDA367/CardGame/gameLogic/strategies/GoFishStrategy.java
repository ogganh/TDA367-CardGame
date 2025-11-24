package TDA367.CardGame.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;

public class GoFishStrategy implements GameStrategy {

    public void setup(GameState state) {
        // deal cards etc.
    }

    public void handleTurn(GameState state, PlayerAction action) {
        // logic for asking a card, drawing from deck, etc.
    }

    public boolean isGameOver(GameState state) {
        // check if all books (pairs) are made
        return false; // placeholder
    }
}