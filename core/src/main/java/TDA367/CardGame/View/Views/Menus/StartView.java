package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * The start menu
 */
public class StartView implements ViewInterface {

    Column buttons;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Texture atlas;

    MainView mainView;

    public StartView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Initializes the start view. Creates a column currently containing a single start button
     */
    @Override
    public void CreateView() {
        atlas = new Texture("CardGameUI.png");
        ViewInformation.font.getData().setScale(0.5f);

        // Column containing buttons
        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2), 100);

        // Start game button
        Button startButton = new Button(
            ViewInformation.font,
            "Start Game",
            new Sprite(atlas, 32, 0 ,16,16)
        );
        // Add an "Action" to the button, a function that is run when clicked
        startButton.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                mainView.getController().setupGame();
                mainView.GoFish();
            }
        });
        startButton.SetScale(8,3);

        Button quitButton = new Button(
            ViewInformation.font,
            "Quit",
            new Sprite(atlas, 32, 0 ,16,16)
        );
        // Add an "Action" to the button, a function that is run when clicked
        quitButton.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                Gdx.app.exit();
            }
        });
        quitButton.SetScale(8,3);

        // Add the start button the column of buttons
        buttons.AddUIElement(startButton);
        buttons.AddUIElement(quitButton);

    }

    /**
     * No logic is required on a frame by frame basis in the start screen
     */
    @Override
    public void Update() {}

    @Override
    public void UpdateState() {

    }

    /**
     * Get the mouse position from the MainView and pass it to the buttons.
     */
    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        buttons.MouseUpdate(mousePosition);
    }

    /**
     * Render the button
     */
    @Override
    public void Draw(SpriteBatch batch) {
        buttons.Draw(batch);
    }
}
