package TDA367.CardGame;
import TDA367.CardGame.controller.GameController;
import com.badlogic.gdx.ApplicationAdapter;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
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
        gameController.getViewport().update(width, height, true); // true centers the camera
    }
}