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

public class GameSelectView implements ViewInterface {
    private MainView mainView;

    private Texture gameSelectBackground;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    // Column column = new Column(new Vector2(ViewInformation.screenSize.x/2,
    // ViewInformation.screenSize.y/2 + 50), 20);

    Row row = new Row(new Vector2(ViewInformation.screenSize.x / 4, 3 * ViewInformation.screenSize.y / 4),
            100);

    public GameSelectView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void createView() {

        Column temp;
        gameSelectBackground = new Texture(Gdx.files.internal("textures/gameselect_background.png"));
        Button gofish = new GreenButton(
                ViewInformation.font,
                "GoFish");
        // Add an "Action" to the button, a function that is run when clicked
        gofish.changeAction(new ButtonAction() {
            @Override
            public void action() {
                mainView.getController().setupGame();
                mainView.goFish();
            }
        });

        gofish.setScale(5, 3);

        temp = new Column(new Vector2(0, 0), 20);
        temp.addUIElement(gofish);

        row.addUIElement(temp);
        /*
         * for (int i = 0; i < 2; i++) {
         * temp = new Column(new Vector2(0,0),20);
         * for (int j = 0; j < 5; j++) {
         * temp.AddUIElement(UIElementFactory.CreateText(ViewInformation.font, "Test"));
         * }
         * row.AddUIElement(temp);
         * }
         */

    }

    @Override
    public void update() {

    }

    @Override
    public void updateState() {

    }

    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        row.mouseUpdate(mousePosition);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // column.draw(batch);
        batch.draw(gameSelectBackground, 0, 0, screenWidth, screenHeight);
        row.draw(batch);
    }
}
