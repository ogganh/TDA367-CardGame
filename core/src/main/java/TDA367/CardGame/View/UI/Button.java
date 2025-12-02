package TDA367.CardGame.View.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button implements UIElement {
    BitmapFont font;
    String text;
    Vector2 position;
    Vector2 size;
    GlyphLayout layout;
    private boolean isClicked = false; // lägger till för att spåra om man rör knappen

    public Button(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
        layout = new GlyphLayout(font, text);
        this.position = new Vector2(0,0); //här
        this.size = new Vector2(layout.width + 10, layout.height + 10);



    }

    @Override
    public void Draw(SpriteBatch batch) {

        font.setColor(new Color(255,255,255,255));
        font.draw(batch, text, position.x - layout.width / 2, position.y + layout.height / 2); // här
    }
    public void Update(Vector2 mousePos, boolean justTouched) {
        //skapa gränser för knappen
        Rectangle bounds = new Rectangle(
            position.x - layout.width / 2,
            position.y - layout.height / 2,
            layout.width,
            layout.height
        );

        //kontroller interaktionen
        if (justTouched && bounds.contains(mousePos.x, mousePos.y)) {
            isClicked = true;
        }

    }

    public boolean IsClicked() {
        boolean result = isClicked;
        isClicked = false;
        return result;                       // återställer efter att ha kollat
    }

    @Override
    public void SetPosition(float x, float y) {
        position = new Vector2(x,y);
    }

    @Override
    public Vector2 GetPosition() {
        return position;
    }

    @Override
    public Vector2 GetSize() {
        return size;
    }

    @Override
    public void SetOrigin(float x, float y) {

    }

    @Override
    public void SetRotation(float angle) {

    }
}
