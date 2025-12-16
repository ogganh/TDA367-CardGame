package TDA367.CardGame.View.UI.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import TDA367.CardGame.View.UI.UIElement;

import java.util.ArrayList;
import java.util.List;

public class Row implements UIElement{
    private Vector2 position;
    private int spaceBetween;
    private List<UIElement> UIElements = new ArrayList<>();

    /**
     * Creates Row for UIelements
     * */
    public Row(Vector2 position ,int spaceBetween) {
        this.spaceBetween = spaceBetween;
        this.position = position;
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
        //int widthMargin = spaceBetween / amountElements;

        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).setPosition(position.x + spaceBetween * i, position.y);
        }
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

    @Override
    public void mouseUpdate(Vector2 mousePos) {
        for (UIElement uiElement : UIElements) {
            uiElement.mouseUpdate(mousePos);
        }
    }

    @Override
    public void setScale(float x, float y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setScale'");
    }
}
