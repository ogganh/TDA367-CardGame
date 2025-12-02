package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Row implements UIElement{
    private Vector2 position;
    private int width;
    private List<UIElement> UIElements = new ArrayList<>();

    /**
     * Creates Row for UIelements
     * */
    public Row(Vector2 position ,int width) {
        this.width = width;
        this.position = position;
    }

    public void AddUIElement(UIElement element){
        UIElements.add(element);
        SpaceElements();
    }
    public void AddUIElement(List<UIElement> elementList){
        UIElements.addAll(elementList);
        SpaceElements();
    }
    private void SpaceElements(){
        int amountElements = UIElements.size();
        int widthMargin = width / amountElements;

        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).SetPosition(position.x + widthMargin * i, position.y);
        }
    }

    @Override
    public void Draw(SpriteBatch batch) {

    }

    @Override
    public void SetPosition(float x, float y) {

    }

    @Override
    public Vector2 GetPosition() {
        return null;
    }

    @Override
    public Vector2 GetSize() {
        return null;
    }

    @Override
    public void SetOrigin(float x, float y) {

    }

    @Override
    public void SetRotation(float angle) {

    }

    @Override
    public void MouseUpdate(Vector2 mousePos) {

    }

    @Override
    public void Click() {
        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).Click();
        }
    }
}
