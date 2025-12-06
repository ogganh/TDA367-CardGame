package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.model.GameState;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
    public void createView() {
        scores = new Column(new Vector2(
            ViewInformation.screenSize.x / 3,
            3 * ViewInformation.screenSize.y / 4 ), columnSpace);

        int winner = 0;
        for (int i = 0; i < state.getPlayers().size(); i++) {
            if (state.getBookCount(i) > state.getBookCount(winner)) winner = i;

        }
        Row row = new Row(new Vector2(0,0), rowSpace);
        row.addUIElement(new Text(ViewInformation.font, "Winner:"));
        row.addUIElement(new Text(ViewInformation.font, String.valueOf(winner +1)));
        scores.addUIElement(row);

        Row titles = new Row(new Vector2(100,100), 100);
        titles.addUIElement(new Text(ViewInformation.font, "Player"));
        titles.addUIElement(new Text(ViewInformation.font, "Books"));
        scores.addUIElement(titles);


        for (int i = 0; i < state.getPlayers().size(); i++) {
            row = new Row(new Vector2(0,0), rowSpace);
            row.addUIElement(new Text(ViewInformation.font, String.valueOf(i)));
            row.addUIElement(new Text(ViewInformation.font, String.valueOf(state.getBookCount(i))));
            scores.addUIElement(row);
        }

        menu = new Button(
            ViewInformation.font,
            "Menu",
            new Sprite(ViewInformation.uiAtlas, 32, 0 ,16,16)
        );
        menu.changeAction(new ButtonAction() {
            @Override
            public void action() {
                view.startView();
            }
        });
        menu.setScale(5,3);
        menu.setPosition(350, 50);


    }

    @Override
    public void update() {

    }

    @Override
    public void updateState() {

    }

    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        menu.mouseUpdate(mousePosition);
    }

    @Override
    public void draw(SpriteBatch batch) {
        scores.draw(batch);
        menu.draw(batch);
    }
}
