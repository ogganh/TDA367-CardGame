package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIElement {
    void draw(SpriteBatch batch);
    void setPosition(float x, float y);
    Vector2 getPosition();
    Vector2 getSize();
    void setOrigin(float x, float y);
    void setRotation(float angle);
    void mouseUpdate(Vector2 mousePos);
}
