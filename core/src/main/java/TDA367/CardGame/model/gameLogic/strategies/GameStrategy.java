package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;

/**
 * Interface for a rule set for different card games. Used by GoFishRules and
 * can be implemented for other games.
 */

public interface GameStrategy {
    /**
     * Initializes the game using a GameState, e.g., deals cards to players.
     */
    void setup(GameState state);

    /**
     * Takes input from PlayerAction and updates the GameState according to the
     * game's rules.
     */
    void handleTurn(GameState state, PlayerAction action);

    public void endTurn();

    int getCurrentPlayerIndex();

    /**
     * Called by GameController after each round to check whether the game is over.
     */
    boolean isGameOver(GameState state);
}
