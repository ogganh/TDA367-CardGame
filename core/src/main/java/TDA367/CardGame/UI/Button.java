package TDA367.CardGame.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Button implements UIElement {
    Sprite sprite;
    public Button(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
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
}
