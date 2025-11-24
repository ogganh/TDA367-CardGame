package TDA367.CardGame;

import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.gameLogic.GameContext;
import TDA367.CardGame.gameLogic.strategies.GoFishStrategy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    FitViewport viewport;

    GameContext gameContext;

    BitmapFont font;
    MainView mainView;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), false);

        font.getData().setScale(0.5f);
        viewport = new FitViewport(1980 / 4, 1080 / 4);
        mainView = new MainView(font,viewport);

        gameContext = new GameContext(new GoFishStrategy());
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
    }

    private void logic() {
        mainView.Update();
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