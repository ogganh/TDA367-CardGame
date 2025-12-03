package TDA367.CardGame.View.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface ViewInterface {
    /**
     * Initialization of the view
     */
    void CreateView();
    /**
     * Logic loop
     */
    void Update();
    /**
     * Updates the state
     */
    void UpdateState();
    /**
     * Handles mouse movement
     * @param mousePosition
     */
    void MouseUpdate(Vector2 mousePosition);
    /**
     * Render loop
     * @param batch
     */
    void Draw(SpriteBatch batch);
    /**
     *
     */
}
