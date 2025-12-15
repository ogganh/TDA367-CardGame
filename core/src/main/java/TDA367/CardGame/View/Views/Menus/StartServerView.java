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
public class StartServerView implements ViewInterface {

    Column buttons;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    MainView mainView;

    private Texture menuBackground;

    public StartServerView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Initializes the start view. Creates a column currently containing a single
     * start button
     */
    @Override
    public void createView() {
        ViewInformation.font.getData().setScale(0.5f);

        menuBackground = new Texture(Gdx.files.internal("textures/menu_background.png"));

        // Column containing buttons
        buttons = new Column(new Vector2(screenWidth / 2, screenHeight / 2 + 50), 50);

        // Join server button
        Button joinServerButton = new Button(
                ViewInformation.font,
                "Host server",
                new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));
        // Add an "Action" to the button, a function that is run when clicked
        joinServerButton.changeAction(new ButtonAction() {
            @Override
            public void action() {
                mainView.getController().startServer();
            }
        });
        joinServerButton.setScale(8, 3);

        // Add the start button the column of buttons
        buttons.addUIElement(joinServerButton);

    }

    /**
     * No logic is required on a frame by frame basis in the start screen
     */
    @Override
    public void update() {

    }

    @Override
    public void updateState() {

    }

    /**
     * Get the mouse position from the MainView and pass it to the buttons.
     */
    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        buttons.mouseUpdate(mousePosition);
    }

    /**
     * Render the button
     */
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(menuBackground, 0, 0, screenWidth, screenHeight);

        buttons.draw(batch);
    }
}