package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Column implements UIElement{
    private Vector2 position;
    private int spaceBetween;
    private List<UIElement> UIElements = new ArrayList<>();

    /**
     * Creates Column for UIelements
     * */
    public Column(Vector2 position, int spaceBetween) {
        this.position = position;
        this.spaceBetween = spaceBetween;
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
        //int heightMargin = height / amountElements;

        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).setPosition(position.x, position.y - spaceBetween * i);
        }
    }
    public void setPositionInt(int x, int y){
        position = new Vector2(x,y);
    }
    @Override
    public void draw(SpriteBatch batch) {
        for (UIElement uiElement : UIElements) {
            uiElement.draw(batch);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        position = new Vector2(x,y);
        spaceElements();
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getSize() {
        return null;
    }

    @Override
    public void setOrigin(float x, float y) {

    }

    @Override
    public void setRotation(float angle) {

    }

    /**
     * Runs the function on every element in the Column
     */
    public void mouseUpdate(Vector2 mousePos){
        for (UIElement uiElement : UIElements) {
            uiElement.mouseUpdate(mousePos);
        }
    }
}
