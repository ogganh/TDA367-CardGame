package TDA367.CardGame.Views;

import TDA367.CardGame.UI.Button;
import TDA367.CardGame.UI.UIElement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class StartView implements ViewInterface{
    List<UIElement> uiElementList = new ArrayList<>();

    @Override
    public void CreateView() {
        //uiElementList.add(new Button(new Vector2(0,0),new Texture("libgdx.png")));
    }

    @Override
    public void Update() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {

    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < uiElementList.size(); i++) {
            //uiElementList.get(i).Draw(batch);
        }
    }


}
