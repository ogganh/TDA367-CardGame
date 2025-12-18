package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Text implements UIElement{
    private BitmapFont font;
    private String text;
    private Vector2 position;
    private GlyphLayout layout;

    /**
     * Creates Text
     * */
    public Text(BitmapFont font, String text) {
        this.text = text;
        this.font = font;
        this.position = new Vector2(0,0);
        //layout = new GlyphLayout(font, text);
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public void draw(SpriteBatch batch) {
        font.setColor(new Color(255,255,255,255));
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public void setPosition(float x, float y) {
        position = new Vector2(x,y);
    }

    @Override
    public Vector2 getPosition() {
        return null;
    }

    @Override
    public Vector2 getSize() {
        return null;
    }

    @Override
    public void setOrigin(float x, float y) {

    }

    @Override
    public void setRotation(float angle) {

    }

    @Override
    public void mouseUpdate(Vector2 mousePos) {

    }

    @Override
    public void addUIElement(UIElement element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUIElement'");
    }

    @Override
    public void setScale(float x, float y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setScale'");
    }
}
