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

public class MiddleScreen implements ViewInterface{
    GameState state;
    GameController controller;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Column buttons;

    public MiddleScreen(GameState state, GameController controller) {
        this.state = state;
        this.controller = controller;
    }

    @Override
    public void CreateView() {
        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2 + 50), 50);
        Button btn = new Button(
            ViewInformation.font,
            "Next player",
            new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));

        // Add a "on click" function to the guess button
        btn.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                // Send the input to the controller if a card is selected
                state.closeMiddleScreen();
                controller.setCurrentView(ViewType.GO_FISH);
            }
        });
        btn.SetScale(10, 10);
        buttons.AddUIElement(btn);
    }

    @Override
    public void Update() {

    }

    @Override
    public void UpdateState() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        buttons.MouseUpdate(mousePosition);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        buttons.Draw(batch);
    }
}
