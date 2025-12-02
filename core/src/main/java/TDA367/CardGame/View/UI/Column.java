package TDA367.CardGame.View.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Column implements UIElement{
    private Vector2 position;
    private int height;
    private List<UIElement> UIElements = new ArrayList<>();

    /**
     * Creates Column for UIelements
     * */
    public Column(Vector2 position, int height) {
        this.position = position;
        this.height = height;
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
        int heightMargin = height / amountElements;

        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).SetPosition(position.x, position.y - heightMargin * i + height);
        }
    }
    public void SetPosition(int x, int y){
        position = new Vector2(x,y);
    }
    @Override
    public void Draw(SpriteBatch batch) {
        for (UIElement uiElement : UIElements) {
            uiElement.Draw(batch);
        }
    }

    @Override
    public void SetPosition(float x, float y) {
        position = new Vector2(x,y);
    }

    @Override
    public Vector2 GetPosition() {
        return position;
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
    public void MouseUpdate(Vector2 mousePos){
        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).MouseUpdate(mousePos);
        }
    }

    public void Click(){
        for (int i = 0; i < UIElements.size(); i++) {
            UIElements.get(i).Click();
            Gdx.app.log("Column","Clicked element " + i);
        }
    }
}
