package TDA367.CardGame.View.UI.cards;

import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Card implements UIElement {
    private Sprite sprite;
    private int index;

    /**
     * Creates a card
     * @param sprite - card sprite
     * @param index - the index of the card in the texture
     * */
    public Card(Sprite sprite, int index) {
        this.sprite = sprite;
        this.index = index;
        this.sprite.setScale(ViewInformation.cardScale);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Linearly moves the card to its target position
     * @param x - target x
     * @param y - target y
     * */
    public void LerpPosition(float x, float y){
        Vector2 targetPosition = new Vector2(x,y);
        Vector2 direction = targetPosition.sub(sprite.getX(), sprite.getY());
        if (direction.len() <= ViewInformation.lerpSpeed) {
            sprite.setPosition(x,y);
            return;

        }
        direction = direction.nor();

        sprite.setPosition(sprite.getX() + direction.x  * ViewInformation.lerpSpeed, sprite.getY() + direction.y  * ViewInformation.lerpSpeed);
    }

    @Override
    public void SetPosition(float x, float y) {
        // position, target position.
        sprite.setPosition(x,y);
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
