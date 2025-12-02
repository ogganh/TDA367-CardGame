package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIElement {
    void Draw(SpriteBatch batch);
    void SetPosition(float x, float y);
    Vector2 GetPosition();
    Vector2 GetSize();
    void SetOrigin(float x, float y);
    void SetRotation(float angle);
    void MouseUpdate(Vector2 mousePos);
}
