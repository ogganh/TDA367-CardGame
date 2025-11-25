package TDA367.CardGame;

import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.GoFish;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.gameLogic.GameContext;
import TDA367.CardGame.gameLogic.strategies.GoFishStrategy;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.card_logic.Card;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    FitViewport viewport;

    GameContext gameContext;

    BitmapFont font;
    MainView mainView;

    GoFishUserPlayer player1;
    GoFishUserPlayer player2;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), false);

        font.getData().setScale(0.5f);
        viewport = new FitViewport(1980 / 4, 1080 / 4);
        mainView = new MainView(font,viewport);

        player1 = new GoFishUserPlayer("Player 1");
        player2 = new GoFishUserPlayer("Player 2");

        CardDeck deck = new CardDeck();

        gameContext = new GameContext(new GoFishStrategy(java.util.Arrays.asList(player1, player2), deck));
    }

    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            mainView.GoFish();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            gameContext.handleTurn(new PlayerAction(gameContext.getCurrentPlayerIndex(), "ask", "ACE", "HEARTS"));
            Gdx.app.log("Action", "Player " + Integer.toString(gameContext.getCurrentPlayerIndex()+1) + " asked for ACEs");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            gameContext.handleTurn(new PlayerAction(gameContext.getCurrentPlayerIndex(), "ask", "TWO", "HEARTS"));
            Gdx.app.log("Action", "Player " + Integer.toString(gameContext.getCurrentPlayerIndex()+1) + " asked for TWOs");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            gameContext.handleTurn(new PlayerAction(gameContext.getCurrentPlayerIndex(), "ask", "THREE", "HEARTS"));
            Gdx.app.log("Action", "Player " + Integer.toString(gameContext.getCurrentPlayerIndex()+111) + " asked for THREEs");
        }



        // LOG HANDS FOR DEBUGGING
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
            Gdx.app.log("Player1Hand", "---------Player-1-----------");
            Gdx.app.log("Player1Hand", "Hand size: " + Integer.toString(player1.get_hand().size()));
            for (Card card : player1.get_hand()){
                Gdx.app.log("Player1Hand", card.getRank() + " of " + card.getSuit());
            }
            Gdx.app.log("Player1Hand", "----------------------------");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){
            Gdx.app.log("Player2Hand", "---------Player-2-----------");
            Gdx.app.log("Player2Hand", "Hand size: " + Integer.toString(player2.get_hand().size()));
            for (Card card : player2.get_hand()){
                Gdx.app.log("Player2Hand", card.getRank() + " of " + card.getSuit());
            }
            Gdx.app.log("Player1Hand", "----------------------------");
        }
    }

    private void logic() {
        mainView.Update();

        // Select card on mouse click
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            GoFish currentView = (GoFish) mainView.currentView;
            
            currentView.SelectCard();

            CardConversion converter = new CardConversion();
            
            String rank = converter.IntToRank(currentView.GetSelectedCard());
            
            gameContext.handleTurn(new PlayerAction(gameContext.getCurrentPlayerIndex(), "ask", rank, "HEARTS"));
            
            Gdx.app.log("Action", "Player " + Integer.toString(gameContext.getCurrentPlayerIndex()+1) + " asked for " + rank + "s");
        }
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        mainView.Draw(spriteBatch);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }
}