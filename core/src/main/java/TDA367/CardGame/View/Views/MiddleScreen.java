package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class MiddleScreen implements ViewInterface{
    List<UIElement> elements = new ArrayList<>();

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    public MiddleScreen() {
    }

    @Override
    public void CreateView() {
        //elements.add(UIElementFactory.CreateButton(ViewInformation.font, "Press Enter for next player"));
        elements.get(0).SetPosition(screenWidth/2, screenHeight /2);
    }

    @Override
    public void Update() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {

    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).Draw(batch);
        }
    }
}
