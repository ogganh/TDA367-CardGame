package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewController;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.controller.GameController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameSelectView implements ViewInterface {
    private ViewController mainView;

    private Texture gameSelectBackground;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;
    GameController gameController;
    // Column column = new Column(new Vector2(ViewInformation.screenSize.x/2,
    // ViewInformation.screenSize.y/2 + 50), 20);

    Row row = new Row(new Vector2(ViewInformation.screenSize.x / 4, 3 * ViewInformation.screenSize.y / 4),
            100);

    public GameSelectView(ViewController mainView, GameController gameController) {
        this.mainView = mainView;
        this.gameController = gameController;
    }

    @Override
    public void createView() {

        //Column temp;
        gameSelectBackground = new Texture(Gdx.files.internal("textures/gameselect_background.png"));
        Button gofish = new GreenButton(
                ViewInformation.font,
                "GoFish");
        // Add an "Action" to the button, a function that is run when clicked
        gofish.changeAction(new ButtonAction() {
            @Override
            public void action() {
                gameController.StartGofish();

                mainView.goFish();
            }
        });
        gofish.setScale(5, 3);

        Button plump = new GreenButton(
                ViewInformation.font,
                "Plump");
        // Add an "Action" to the button, a function that is run when clicked
        plump.changeAction(new ButtonAction() {
            @Override
            public void action() {
                //gameController.();

                mainView.Plump();
            }
        });
        plump.setScale(5, 3);

        // temp = new Column(new Vector2(0, 0), 20);
        // temp.addUIElement(gofish);
        // temp.addUIElement(plump);

        //row.addUIElement(temp);
        row.addUIElement(gofish);
        row.addUIElement(plump);
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
