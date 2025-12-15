package TDA367.CardGame;

import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    GameController gameController;

    @Override
    public void create() {
        // Create shared viewport and game state
        FitViewport viewport = new FitViewport(1980 / 4, 1080 / 4);
        GameState gameState = new GameState();

        // Create view and controller, then wire them together
        MainView view = new MainView(viewport, gameState);
        gameController = new GameController(viewport, gameState, view);
        view.setController(gameController);
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