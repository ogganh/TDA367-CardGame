package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.model.GameState;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.tool.View;

public class EndScoreView implements ViewInterface {
    private GameState state;
    private MainView view;
    private Column scores;

    private Button menu;

    private int columnSpace = 40;
    private int rowSpace = 100;


    public EndScoreView(GameState state, MainView mainView){
        this.state = state;
        this.view = mainView;
    }

    @Override
    public void CreateView() {
        scores = new Column(new Vector2(
            ViewInformation.screenSize.x / 3,
            3 * ViewInformation.screenSize.y / 4 ), columnSpace);

        int winner = 0;
        for (int i = 0; i < state.getPlayers().size(); i++) {
            if (state.getBookCount(i) > state.getBookCount(winner)) winner = i;

        }
        Row row = new Row(new Vector2(0,0), rowSpace);
        row.AddUIElement(new Text(ViewInformation.font, "Winner:"));
        row.AddUIElement(new Text(ViewInformation.font, String.valueOf(winner +1)));
        scores.AddUIElement(row);

        Row titles = new Row(new Vector2(0,0), rowSpace);
        titles.AddUIElement(new Text(ViewInformation.font, "Player"));
        titles.AddUIElement(new Text(ViewInformation.font, "Books"));
        scores.AddUIElement(titles);


        for (int i = 0; i < state.getPlayers().size(); i++) {
            row = new Row(new Vector2(0,0), rowSpace);
            row.AddUIElement(new Text(ViewInformation.font, String.valueOf(i)));
            row.AddUIElement(new Text(ViewInformation.font, String.valueOf(state.getBookCount(i))));
            scores.AddUIElement(row);
        }

        menu = new Button(
            ViewInformation.font,
            "Menu",
            new Sprite(ViewInformation.uiAtlas, 32, 0 ,16,16)
        );
        menu.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                view.StartView();
            }
        });
        menu.SetScale(5,3);
        menu.SetPosition(350, 50);


    }

    @Override
    public void Update() {

    }

    @Override
    public void UpdateState() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        menu.MouseUpdate(mousePosition);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        scores.Draw(batch);
        menu.Draw(batch);
    }
}
