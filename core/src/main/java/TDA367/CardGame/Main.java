package TDA367.CardGame;

import TDA367.CardGame.controller.GameController;

import TDA367.CardGame.View.Views.MainView;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    FitViewport viewport;

    BitmapFont font;
    MainView mainView;

    GameController gameController;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), false);

        font.getData().setScale(0.5f);
        viewport = new FitViewport(1980 / 4, 1080 / 4);
        mainView = new MainView(font, viewport);

        gameController = new GameController(mainView);
    }

    @Override
    public void render() {
        gameController.update();
    }

    @Override
    public void dispose() {
        gameController.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }
}