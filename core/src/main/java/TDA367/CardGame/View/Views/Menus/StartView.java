package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewController;
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
    private ViewController mainView;
    private Texture menuBackground;

    private UIElement buttons;

    public StartView(ViewController mainView) {
        this.mainView = mainView;
    }

    /**
     * Initializes the start view. Creates a column currently containing a single start button
     */
    @Override
    public void createView() {
        ViewInformation.font.getData().setScale(0.5f);

        menuBackground = new Texture(Gdx.files.internal("textures/menu_background.png"));

        // Column containing buttons
        buttons = UIElementFactory.createColumn(new Vector2(ViewInformation.screenSize.x/2, ViewInformation.screenSize.y /2 + 50), 50);

        // Start game button
        UIElement startButton = UIElementFactory.createGreenButton(
            ViewInformation.font,
            "Start Game",
            new ButtonAction() {
            @Override
            public void action() {
                mainView.gameSelect();
            }
        }
        );
        // Add an "Action" to the button, a function that is run when clicked
        startButton.setScale(8,3);

        UIElement quitButton = UIElementFactory.createGreenButton(
            ViewInformation.font,
            "Quit",
            new ButtonAction() {
            @Override
            public void action() {
                Gdx.app.exit();
            }
        }
        );
        // Add an "Action" to the button, a function that is run when clicked
        quitButton.setScale(8,3);

        // Add the start button the column of buttons
        buttons.addUIElement(startButton);
        buttons.addUIElement(quitButton);

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
        batch.draw(menuBackground, 0, 0, ViewInformation.screenSize.x, ViewInformation.screenSize.y);

        buttons.draw(batch);
    }
}
