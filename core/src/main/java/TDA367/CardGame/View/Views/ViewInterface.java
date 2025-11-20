package TDA367.CardGame.View.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface ViewInterface {
    void CreateView();
    void Update();
    void MouseUpdate(Vector2 mousePosition);
    void Draw(SpriteBatch batch);
}
