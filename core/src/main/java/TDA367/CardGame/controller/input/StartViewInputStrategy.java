package TDA367.CardGame.controller.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.View.Views.ViewType;

public class StartViewInputStrategy implements InputStrategy {
    GameController gameController;
    public StartViewInputStrategy(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameController.setCurrentView(ViewType.GO_FISH);
        }
    }
}