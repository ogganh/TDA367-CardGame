package TDA367.CardGame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewType;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.gameLogic.GameContext;
import TDA367.CardGame.model.gameLogic.strategies.GoFishRules;

public class GameController {
    private MainView view;
    private SpriteBatch spriteBatch;


    private GameContext gameContext;
    
    private GameState gameState = new GameState();

    private FitViewport viewport = new FitViewport(1980 / 4, 1080 / 4);
    private MainView mainView;

    public GameController() {
        this.spriteBatch = new SpriteBatch();
        this.mainView = new MainView(viewport, gameState, this);
        this.view = mainView;

        // Initialize InputController with this GameController
        setCurrentView(ViewType.START);
    }

    public void setup() {
        gameState.addPlayer(new GoFishUserPlayer("Player 1"));
        gameState.addPlayer(new GoFishUserPlayer("Player 2"));
        gameState.addPile("lake", new CardDeck());
        gameContext = new GameContext(gameState, new GoFishRules(gameState.getPlayers(), gameState.getPile("lake")));
        setCurrentView(ViewType.GO_FISH);
        Gdx.app.log("GameController", "Setting up game with players: " + gameState.getPlayers().toString());
    }

    public GameContext getGameContext() { return gameContext; }

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
        }
    }

    public void handleAction(int sourcePlayerIndex, String action, String rank, String suit) {
        gameContext.handleTurn(new PlayerAction(sourcePlayerIndex, null, rank, suit));
    }

    public void update() {
        view.Update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        view.Draw(spriteBatch);
    }

    public void dispose() {
        spriteBatch.dispose();
    }

    public FitViewport getViewport() {
        return viewport;
    }
}
