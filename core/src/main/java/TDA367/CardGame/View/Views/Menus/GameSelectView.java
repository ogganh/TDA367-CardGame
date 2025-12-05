package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewInterface;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameSelectView implements ViewInterface {
    MainView mainView;

    //Column column = new Column(new Vector2(ViewInformation.screenSize.x/2, ViewInformation.screenSize.y/2 + 50), 20);

    Row row = new Row(new Vector2(ViewInformation.screenSize.x/4, 3 * ViewInformation.screenSize.y/4),
        100);

    public GameSelectView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void CreateView() {
        Column temp;
        Button gofish = new Button(
            ViewInformation.font,
            "GoFish",
            new Sprite(ViewInformation.uiAtlas, 32, 0 ,16,16)
        );
        // Add an "Action" to the button, a function that is run when clicked
        gofish.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                mainView.getController().setupGame();
                mainView.GoFish();
            }
        });

        gofish.SetScale(5,3);

        temp = new Column(new Vector2(0,0),20);
        temp.AddUIElement(gofish);

        row.AddUIElement(temp);
/*         for (int i = 0; i < 2; i++) {
            temp = new Column(new Vector2(0,0),20);
            for (int j = 0; j < 5; j++) {
                temp.AddUIElement(UIElementFactory.CreateText(ViewInformation.font, "Test"));
            }
            row.AddUIElement(temp);
        } */

    }

    @Override
    public void Update() {

    }

    @Override
    public void UpdateState() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        row.MouseUpdate(mousePosition);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        //column.Draw(batch);
        row.Draw(batch);
    }
}
