package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Button implements UIElement {
    BitmapFont font;
    String text;
    Vector2 position;
    GlyphLayout layout;

    float width;
    float height;

    ButtonAction action;


    public Button(BitmapFont font, String text, float width, float height) {
        this.font = font;
        this.text = text;
        this.width = width;
        this.height = height;

        layout = new GlyphLayout(font, text);
    }

    public void ClickCheck(Vector2 mousePos){
        if (mousePos == null) return;

        if (mousePos.x > position.x && mousePos.x < position.x + width){
            if (mousePos.y > position.y && mousePos.y < position.y + height){
                action.Action();
            }
        }
    }

    @Override
    public void Draw(SpriteBatch batch) {

        font.setColor(new Color(255,255,255,255));
        font.draw(batch, text, position.x - layout.width / 2, position.y);
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
    public void ChangeAction(ButtonAction action){
        this.action  = action;
    }
}
