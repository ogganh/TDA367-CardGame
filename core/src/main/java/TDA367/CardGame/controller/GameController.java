package TDA367.CardGame.controller;

import TDA367.CardGame.View.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewType;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.gameLogic.GameContext;
import TDA367.CardGame.model.gameLogic.strategies.GoFishRules;


/**
 * The GameController instantiates most objects and handles the communication from the view to the model amongst other things.
 */
public class GameController {
    private FitViewport viewport = new FitViewport(1980 / 4, 1080 / 4);    // Window
    private MainView view;  // Manages the UI
    private SpriteBatch spriteBatch;
    private GameContext gameContext;    // Manages the rules
    private GameState gameState = new GameState();  // The state keeps track of the current games data

    /**
     * The controller constructor creates the MainView and sets it to display the start menu
     */
    public GameController() {
        this.spriteBatch = new SpriteBatch();
        SoundManager.load();
        SoundManager.playBGMusic();
        // Create a MainView, rquires a GameState to update the graphics according to the current state of the game
        // and it also requires the controller to relay inputs.
        this.view = new MainView(viewport, gameState, this);

        setCurrentView(ViewType.START); // Enter the start menu
    }

    public void setupGame() {
        // temporary setup hardcoded for 2 player GoFish

        // Add players and deck to gamestate, should probably be moved into the rules?
        gameState.reset();
        gameState.addPlayer(new GoFishUserPlayer("Player 1"));
        gameState.addPlayer(new GoFishUserPlayer("Player 2"));
        gameState.addPile("lake", new CardDeck());

        // Create a context with the gamestate and GoFishRules
        gameContext = new GameContext(gameState, new GoFishRules(gameState.getPlayers(), gameState.getPile("lake")));
        // Change the view to Go fish
        setCurrentView(ViewType.GO_FISH);
        Gdx.app.log("GameController", "Setting up game with players: " + gameState.getPlayers().toString());
    }

    public GameContext getGameContext() { return gameContext; }

    // Change the current view, can probably be implemented in a better way
    public void setCurrentView(ViewType viewType) {
        switch (viewType) {
            case START:
                view.StartView();
                break;
            case GO_FISH:
                view.GoFish();
                break;
            case RULES:
                view.Rules();
                break;
            case MIDDLE_SCREEN:
                view.MiddleScreen();
                break;
            case GAME_SELECT:
                view.GameSelect();
                break;

            case END_SCORE:
                view.EndScreen();
                break;
        }
    }

    /**
     * Run by the view when a player is submitting an action, which the controller passes along to the gamecontext (rules) as a PlayerAction object.
     * @param sourcePlayerIndex - the player taking the action
     * @param action - the type of action, currently unused
     * @param rank - the rank of the card, should preferably be replaced by a Card object to allow for referencing a specific card in games where duplicates may occur
     * @param suit - the suit of the card, should preferably be replaced by a Card object to allow for referencing a specific card in games where duplicates may occur
     */
    public void handleAction(int sourcePlayerIndex, String action, String rank, String suit) {
        gameContext.handleTurn(new PlayerAction(sourcePlayerIndex, null, rank, suit));

        view.UpdateState();


    }

    /**
     * The core update loop of the game, runs every frame
     */
    public void update() {
        view.Update();

        if (gameContext != null && gameContext.isGameOver()) {
            setCurrentView(ViewType.END_SCORE);
            gameContext = null; // Reset the game context to allow for a new game

        }
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        view.Draw(spriteBatch);
    }

    /**
     * Used by libgdx during cleanup when exiting
     */
    public void dispose() {
        spriteBatch.dispose();
        SoundManager.dispose();
    }

    public FitViewport getViewport() {
        return viewport;
    }
}
