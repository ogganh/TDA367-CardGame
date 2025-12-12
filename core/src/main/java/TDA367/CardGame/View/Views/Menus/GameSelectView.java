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


    Row row = new Row(new Vector2(ViewInformation.screenSize.x/4, 3 * ViewInformation.screenSize.y/4),
        100);

    public GameSelectView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void createView() {


        Column temp;
        gameSelectBackground = new Texture(Gdx.files.internal("textures/gameselect_background.png"));
        Button gofish = new Button(
            ViewInformation.font,
            "GoFish",
            new Sprite(ViewInformation.uiAtlas, 32, 0 ,16,16)
        );
        // Add an "Action" to the button, a function that is run when clicked
        gofish.changeAction(new ButtonAction() {
            @Override
            public void action() {
                mainView.getController().setupGame();
                mainView.goFish();
            }
        });

        gofish.setScale(5,3);

        temp = new Column(new Vector2(0,0),20);
        temp.addUIElement(gofish);

        row.addUIElement(temp);

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
        //column.draw(batch);
        batch.draw(gameSelectBackground, 0, 0, ViewInformation.screenSize.x, ViewInformation.screenSize.y);
        row.draw(batch);
    }
}
