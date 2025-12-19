package TDA367.CardGame.View.Views.Menus;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.ViewController;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.model.GameState;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EndScoreView implements ViewInterface {
    private GameState state;
    private ViewController view;
    private UIElement scores;

    private UIElement menu;

    private int columnSpace = 40;
    private int rowSpace = 100;

    public EndScoreView(GameState state, ViewController mainView) {
        this.state = state;
        this.view = mainView;
    }

    @Override
    public void createView() {
        scores = UIElementFactory.createColumn(new Vector2(
            ViewInformation.screenSize.x / 3,
            3 * ViewInformation.screenSize.y / 4 ), columnSpace);

        int winner = 0;
        for (int i = 0; i < state.getPlayers().size(); i++) {
            if (state.getBookCount(i) > state.getBookCount(winner))
                winner = i;

        }
        UIElement row = UIElementFactory.createRow(new Vector2(0,0), rowSpace);
        row.addUIElement(UIElementFactory.createText(ViewInformation.font, "Winner:"));
        row.addUIElement(UIElementFactory.createText(ViewInformation.font, String.valueOf(winner +1)));
        scores.addUIElement(row);

        UIElement titles = UIElementFactory.createRow(new Vector2(100,100), 100);
        titles.addUIElement(UIElementFactory.createText(ViewInformation.font, "Player"));
        titles.addUIElement(UIElementFactory.createText(ViewInformation.font, "Books"));
        scores.addUIElement(titles);

        for (int i = 0; i < state.getPlayers().size(); i++) {
            row = UIElementFactory.createRow(new Vector2(0,0), rowSpace);
            row.addUIElement(UIElementFactory.createText(ViewInformation.font, String.valueOf(i)));
            row.addUIElement(UIElementFactory.createText(ViewInformation.font, String.valueOf(state.getBookCount(i))));
            scores.addUIElement(row);
        }

        menu = UIElementFactory.createButton(
            ViewInformation.font,
            "Menu",
            new Sprite(ViewInformation.uiAtlas, 32, 0 ,16,16),
            new ButtonAction() {
            @Override
            public void action() {
                view.startView();
            }
        }
        );
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
