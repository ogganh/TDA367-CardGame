package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    private Vector2 position;
    private int height;
    private int width;
    private List<UIElement> UIElements;

    public Panel(Vector2 position, int width, int height) {
        this.position = position;
        this.height = height;
        this.width = width;

        UIElements = new ArrayList<>();
    }
    public void addUIElement(UIElement element){
        UIElements.add(element);
        spaceElements();
    }
    public void addUIElement(List<UIElement> elementList){
        UIElements.addAll(elementList);
        spaceElements();
    }
    private void spaceElements(){
        int amountElements = UIElements.size();
        int heightMargin = height / amountElements;
        int widthMargin = width / amountElements;

        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).setPosition(position.x + widthMargin * i, position.y + heightMargin * i);
        }
    }
    public void setPosition(int x, int y){
        position = new Vector2(x,y);
    }
    public void draw(SpriteBatch batch){
        for (UIElement uiElement : UIElements) {
            uiElement.draw(batch);
        }
    }
}
