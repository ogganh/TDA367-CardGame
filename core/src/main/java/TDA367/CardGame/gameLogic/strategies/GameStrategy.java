package TDA367.CardGame.gameLogic.strategies;

import TDA367.CardGame.controller.GameState;
import TDA367.CardGame.controller.PlayerAction;

// The strategy for the different game modes
public interface GameStrategy {
    void setup(GameState state);
    void handleTurn(GameState state, PlayerAction action);
    int getCurrentPlayerIndex();
    boolean isGameOver(GameState state);
}
