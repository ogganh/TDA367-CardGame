package TDA367.CardGame.controller;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.GoFish;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewType;

import TDA367.CardGame.controller.input.GoFishInputStrategy;
import TDA367.CardGame.controller.input.StartViewInputStrategy;
import TDA367.CardGame.gameLogic.GameContext;
import TDA367.CardGame.gameLogic.strategies.GoFishRules;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.controller.input.InputController;
import TDA367.CardGame.controller.input.MiddleScreenInputStrategy;
import TDA367.CardGame.controller.input.RulesInputStrategy;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.card_logic.Card;

public class GameController implements ViewController {
    private MainView view;
    private SpriteBatch spriteBatch;

    private InputController inputController;

    private GameContext gameContext;

    private CardConversion cardConversion = new CardConversion();

    public GameController(MainView mainView) {
        this.spriteBatch = new SpriteBatch();
        this.view = mainView;

        // Initialize InputController with this GameController
        this.inputController = new InputController();
        setCurrentView(ViewType.START);
        setup();
    }

    public void setup() {
        GameState gameState = new GameState();
        gameState.addPlayer(new GoFishUserPlayer("Player 1"));
        gameState.addPlayer(new GoFishUserPlayer("Player 2"));
        gameState.addPile("lake", new CardDeck());
        gameContext = new GameContext(gameState, new GoFishRules(gameState.getPlayers(), gameState.getPile("lake")));
        Gdx.app.log("GameController", "Setting up game with players: " + gameState.getPlayers().toString());
    }

    public GameContext getGameContext() { return gameContext; }

    public void setCurrentView(ViewType viewType) {
        switch (viewType) {
            case START:
                view.StartView(this);
                inputController.setStrategy(new StartViewInputStrategy(this, view.currentView));
                break;
            case GO_FISH:
                view.GoFish(gameContext.getState());

                // Cast current view to GoFish
                GoFish goFishView = (GoFish) view.currentView;

                // --- Current player ---

                // Get current player's hand.
//                List<Card> playerHand = gameContext.getState()
//                        .getPlayers()
//                        .get(gameContext.getCurrentPlayerIndex())
//                        .get_hand();
//
//                // Convert cards to indices for the view.
//                List<Integer> cardIndices = new ArrayList<>();
//                for (Card card : playerHand) {
//                    cardIndices.add(cardConversion.CardToInt(card.getSuit(), card.getRank()));
//                }
//                goFishView.AddCards(cardIndices);

                inputController.setStrategy(new GoFishInputStrategy(this));
                break;
            case RULES:
                view.Rules();
                inputController.setStrategy(new RulesInputStrategy(this));
                break;
            case MIDDLE_SCREEN:
                view.MiddleScreen();
                inputController.setStrategy(new MiddleScreenInputStrategy(this));
                break;
        }
    }

    private void handleInput() {
        inputController.handleInput();
    }

    public void update() {
        view.Update();
        handleInput();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        view.Draw(spriteBatch);
    }

    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void SetGoFish() {
        setCurrentView(ViewType.GO_FISH);
    }
}
