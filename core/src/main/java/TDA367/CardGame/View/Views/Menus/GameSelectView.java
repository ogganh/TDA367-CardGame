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

    UIElement row = UIElementFactory.createRow(new Vector2(ViewInformation.screenSize.x / 4, 3 * ViewInformation.screenSize.y / 4),
            100);

    public GameSelectView(ViewController mainView, GameController gameController) {
        this.mainView = mainView;
        this.gameController = gameController;
    }

    @Override
    public void createView() {

        //Column temp;
        gameSelectBackground = new Texture(Gdx.files.internal("textures/gameselect_background.png"));
        UIElement gofish = UIElementFactory.createGreenButton(
                ViewInformation.font,
                "GoFish",
                new ButtonAction() {
                    @Override
                    public void action() {
                        gameController.StartGofish();

                        mainView.goFish();
                    }
                }
            );
        gofish.setScale(5, 3);

        UIElement plump = UIElementFactory.createGreenButton(
                ViewInformation.font,
                "Plump",
                new ButtonAction() {
                    @Override
                    public void action() {
                        //gameController.();

                        mainView.Plump();
                    }
        });
        plump.setScale(5, 3);

        row.addUIElement(gofish);
        row.addUIElement(plump);
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
