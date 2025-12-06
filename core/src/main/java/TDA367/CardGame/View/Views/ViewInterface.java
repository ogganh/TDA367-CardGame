package TDA367.CardGame.View.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface ViewInterface {
    /**
     * Initialization of the view
     */
    void createView();
    /**
     * Logic loop
     */
    void update();
    /**
     * Updates the state
     */
    void updateState();
    /**
     * Handles mouse movement
     * @param mousePosition
     */
    void mouseUpdate(Vector2 mousePosition);
    /**
     * Render loop
     * @param batch
     */
    void draw(SpriteBatch batch);
    /**
     *
     */
}
