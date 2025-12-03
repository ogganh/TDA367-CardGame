package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Text implements UIElement{
    BitmapFont font;
    String text;
    Vector2 position;
    GlyphLayout layout;

    /**
     * Creates Text
     * */
    public Text(BitmapFont font, String text) {
        this.text = text;
        this.font = font;
        this.position = new Vector2(0,0);
        //layout = new GlyphLayout(font, text);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        font.setColor(new Color(255,255,255,255));
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public void SetPosition(float x, float y) {
        position = new Vector2(x,y);
    }

    @Override
    public Vector2 GetPosition() {
        return null;
    }

    @Override
    public Vector2 GetSize() {
        return null;
    }

    @Override
    public void SetOrigin(float x, float y) {

    }

    @Override
    public void SetRotation(float angle) {

    }

    @Override
    public void MouseUpdate(Vector2 mousePos) {

    }
}
