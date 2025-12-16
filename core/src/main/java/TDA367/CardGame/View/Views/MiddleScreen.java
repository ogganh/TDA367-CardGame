package TDA367.CardGame.View.Views;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.ViewManager;
import TDA367.CardGame.View.UI.ButtonAction;
import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.UI.UIElementFactory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/** The view between player turns. Prohibits player from seeing opponents cards. */

public class MiddleScreen implements ViewInterface{
    private GameState state;
    private ViewManager viewManager;

    private float screenWidth = ViewInformation.screenSize.x;
    private float screenHeight = ViewInformation.screenSize.y;

    private UIElement buttons;

    public MiddleScreen(GameState state, ViewManager viewManager) {
        this.state = state;
        this.viewManager = viewManager;
    }

    @Override
    public void createView() {
        buttons = UIElementFactory.createColumn(new Vector2(screenWidth/2, screenHeight /2 + 50), 50);
        UIElement btn = UIElementFactory.createButton(
            ViewInformation.font,
            "Next player",
            new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16),
        new ButtonAction() {
            @Override
            public void action() {
                // Send the input to the controller if a card is selected
                state.closeMiddleScreen();
                viewManager.goFish();
            }
        }
        );

        // Add a "on click" function to the guess button
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
