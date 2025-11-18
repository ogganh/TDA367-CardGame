package TDA367.CardGame.UI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Card implements UIElement{
    private Sprite sprite;
    public Card(Sprite sprite) {
        this.sprite = sprite;
        //this.sprite.setOriginCenter();

    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void SetPosition(float x, float y) {
        sprite.setPosition(x , y);

        //sprite.setPosition(x - sprite.getWidth() / 2,y - sprite.getHeight()/2);
    }

    @Override
    public Vector2 GetPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public Vector2 GetSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }
}
