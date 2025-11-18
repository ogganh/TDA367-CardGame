package TDA367.CardGame;

import TDA367.CardGame.Views.GoFish;
import TDA367.CardGame.Views.StartView;
import TDA367.CardGame.Views.ViewInterface;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    FitViewport viewport;

    private Texture image;

    StartView startView = new StartView();
    Sprite test;

    GoFish fish;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(1980 / 4, 1080 / 4);

        image = new Texture("card_textures/1.2 Poker cards.png");

        test = new Sprite(image, 48,64);
        fish = new GoFish();
        fish.CreateView();
    }

    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
        draw();
    }

    private void input() {

    }

    private void logic() {
        Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        Vector3 worldPosition = viewport.unproject(cursorPosition);

        fish.MouseUpdate(new Vector2(worldPosition.x, worldPosition.y));
        fish.Update();
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        fish.Draw(spriteBatch);

        //test.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        image.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }
}
