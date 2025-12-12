package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.ButtonAction;
import TDA367.CardGame.View.UI.Column;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.UI.Button;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/** The view between player turns. Prohibits player from seeing opponents cards. */

public class MiddleScreen implements ViewInterface{
    private GameState state;
    private GameController controller;

    private float screenWidth = ViewInformation.screenSize.x;
    private float screenHeight = ViewInformation.screenSize.y;

    private Column buttons;

    public MiddleScreen(GameState state, GameController controller) {
        this.state = state;
        this.controller = controller;
    }

    @Override
    public void createView() {
        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2 + 50), 50);
        Button btn = new Button(
            ViewInformation.font,
            "Next player",
            new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));

        // Add a "on click" function to the guess button
        btn.changeAction(new ButtonAction() {
            @Override
            public void action() {
                // Send the input to the controller if a card is selected
                state.closeMiddleScreen();
                controller.setCurrentView(ViewType.GO_FISH);
            }
        });
        btn.setScale(10, 10);
        buttons.addUIElement(btn);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateState() {

    }
    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        buttons.mouseUpdate(mousePosition);
    }

    @Override
    public void draw(SpriteBatch batch) {
        buttons.draw(batch);
    }
}
