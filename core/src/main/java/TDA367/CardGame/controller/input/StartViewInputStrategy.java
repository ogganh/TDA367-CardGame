package TDA367.CardGame.controller.input;

import TDA367.CardGame.View.Views.ViewInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.View.Views.ViewType;

public class StartViewInputStrategy implements InputStrategy {
    GameController gameController;
    ViewInterface view;
    public StartViewInputStrategy(GameController gameController, ViewInterface view) {
        this.gameController = gameController;
        this.view = view;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameController.setCurrentView(ViewType.GO_FISH);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            view.Click();
        }
    }
}
