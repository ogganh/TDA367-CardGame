package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;

// The strategy for the different game modes
public interface GameStrategy {
    void setup(GameState state);
    void handleTurn(GameState state, PlayerAction action);
    int getCurrentPlayerIndex();
    boolean isGameOver(GameState state);
}
