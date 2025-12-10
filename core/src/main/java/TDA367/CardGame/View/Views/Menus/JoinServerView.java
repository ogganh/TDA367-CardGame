package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * View for joining a server using an input code
 */
public class JoinServerView implements ViewInterface {

    MainView mainView;
    private Texture background;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Column column;
    Text codeText;
    String code = "";

    public JoinServerView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void createView() {
        ViewInformation.font.getData().setScale(0.5f);

        background = new Texture(Gdx.files.internal("textures/menu_background.png"));

        column = new Column(new Vector2(screenWidth / 2, screenHeight / 2 + 50), 60);

        // Display for the entered code
        codeText = new Text(ViewInformation.font, "Code: ");
        codeText.setPosition(screenWidth / 2 - 150, screenHeight / 2 + 40);

        // Button to open native text input (click to type the code)
        Button enterCode = new Button(
                ViewInformation.font,
                "Enter Code",
                new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));
        enterCode.changeAction(new ButtonAction() {
            @Override
            public void action() {
                // Open native text input dialog
                Gdx.input.getTextInput(new TextInputListener() {
                    @Override
                    public void input(final String text) {
                        code = text == null ? "" : text.trim();
                        codeText.setText("Code: " + code);
                    }

                    @Override
                    public void canceled() {
                        // do nothing
                    }
                }, "Enter Join Code", code, "e.g. ABC123");
            }
        });
        enterCode.setScale(6, 3);

        // JOIN button
        Button joinButton = new Button(
                ViewInformation.font,
                "JOIN",
                new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));
        joinButton.changeAction(new ButtonAction() {
            @Override
            public void action() {
                if (code == null || code.isEmpty()) {
                    Gdx.app.log("JoinServerView", "No code entered.");
                } else {
                    Gdx.app.log("JoinServerView", "Joining with code: " + code);
                    // TODO: integrate with networking/client code to perform join
                }
            }
        });
        joinButton.setScale(6, 3);

        // Back button to return to game select
        Button backButton = new Button(
                ViewInformation.font,
                "Back",
                new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));
        backButton.changeAction(new ButtonAction() {
            @Override
            public void action() {
                mainView.gameSelect();
            }
        });
        backButton.setScale(6, 3);

        // Layout: use column for buttons and add code text separately so it can be
        // positioned nicely
        Column buttons = new Column(new Vector2(screenWidth / 2, screenHeight / 2 - 20), 70);
        buttons.addUIElement(enterCode);
        buttons.addUIElement(joinButton);
        buttons.addUIElement(backButton);

        // Keep codeText as separate UI element so it can be drawn above buttons
        column.addUIElement(codeText);
        column.addUIElement(buttons);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateState() {

    }

    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        if (column != null)
            column.mouseUpdate(mousePosition);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        if (column != null)
            column.draw(batch);
    }
}
