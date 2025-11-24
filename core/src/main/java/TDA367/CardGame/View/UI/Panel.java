package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    Vector2 position;
    int breadth;
    int width;
    List<UIElement> UIElements;
    public Panel(int breadth, int width) {
        this.breadth = breadth;
        this.width = width;
        UIElements = new ArrayList<>();
    }
    public void AddUIElements(UIElement element){
        UIElements.add(element);
    }
    public void AddUIElements(List<UIElement> elementList){
        UIElements.addAll(elementList);
    }
    private void SpaceElements(){

    }
    public void SetPosition(int x, int y){
        position = new Vector2(x,y);
    }
    public void Draw(SpriteBatch batch){
        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).Draw(batch);
        }
    }
}
