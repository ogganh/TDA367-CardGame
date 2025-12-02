package TDA367.CardGame.gameLogic;

import TDA367.CardGame.controller.GameState;
import TDA367.CardGame.controller.PlayerAction;
import TDA367.CardGame.gameLogic.strategies.GameStrategy;
import TDA367.CardGame.model.player.UserPlayer;

public class GameContext {
    private GameStrategy rules;
    private GameState state;

    // A constructor to initialize with a specific strategy (GameRules)
    public GameContext(GameState state, GameStrategy rules) {
        this.rules = rules;
        this.state = state;
        rules.setup(state);
    }

    public void setup() {
        rules.setup(state);
    }

    // Handles player actions and updates game state accordingly.
    public void handleTurn(PlayerAction action) {
        rules.handleTurn(state, action); //skickar vidare till action till rules
    }

    // Ends the game and performs any necessary cleanup.
    public boolean isGameOver() {
        return rules.isGameOver(state);
    }

    public int getCurrentPlayerIndex() {
        return rules.getCurrentPlayerIndex();
    }

    public UserPlayer getCurrentPlayer() {
        return state.getPlayers().get(getCurrentPlayerIndex());
    }

    // Getter for the current game state
    public GameState getState() { return state; }

    public GameStrategy getRules() { return rules; }
}
