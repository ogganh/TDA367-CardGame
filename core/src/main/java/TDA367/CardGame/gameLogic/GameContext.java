package TDA367.CardGame.gameLogic;

import TDA367.CardGame.gameLogic.strategies.GameStrategy;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;

public class GameContext {
    private GameStrategy rules;
    private GameState state;

    // A constructor to initialize with a specific strategy (GameRules)
    public GameContext(GameStrategy rules) {
        this.rules = rules;
        this.state = new GameState();
        rules.setup(state);
    }

    public void setup() {
        rules.setup(state);
    }

    // Handles player actions and updates game state accordingly.
    public void handleTurn(PlayerAction action) {
        rules.handleTurn(state, action);
    }

    // Ends the game and performs any necessary cleanup.
    public boolean isGameOver() {
        return rules.isGameOver(state);
    }

    public int getCurrentPlayerIndex() {
        return state.getCurrentPlayerIndex();
    }

    // Getter for the current game state
    public GameState getState() { return state; }
}