package TDA367.CardGame.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Button implements UIElement {
    Vector2 position;
    Vector2 size;
    Texture image;
    public Button(Vector2 position, Texture image) {
        this.image = image;
        //this.size = size;
        this.position = position;

    }
    @Override
    public void Draw(SpriteBatch batch) {
        batch.draw(image, position.x, position.y);

    }
}
