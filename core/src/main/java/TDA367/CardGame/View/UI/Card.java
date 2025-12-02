package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Card implements UIElement{
    private Sprite sprite;
    private int index;

    /**
     * Creates a card
     * */
    public Card(Sprite sprite, int index) {
        this.sprite = sprite;
        this.index = index;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    // TODO: Lerp liknande setposition
    @Override
    public void SetPosition(float x, float y) {
        sprite.setPosition(x , y);
    }

    @Override
    public Vector2 GetPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public Vector2 GetSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    public void SetOrigin(float x, float y){
        sprite.setOrigin(x,y);
    }
    public void SetRotation(float angle){
        sprite.setRotation(angle);
    }

    @Override
    public void MouseUpdate(Vector2 mousePos) {

    }

    public int GetIndex(){
        return index;
    }
}
