package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIElement {
    public void draw(SpriteBatch batch);
    public void setPosition(float x, float y);
    public Vector2 getPosition();
    public Vector2 getSize();
    public void setOrigin(float x, float y);
    public void setRotation(float angle);
    public void mouseUpdate(Vector2 mousePos);
}
