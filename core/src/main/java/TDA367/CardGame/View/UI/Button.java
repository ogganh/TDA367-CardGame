package TDA367.CardGame.View.UI;

import com.badlogic.gdx.Gdx;
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

    Sprite sprite;

    boolean hovering = false;
    ButtonAction action;


    /**
     * Creates a button, sprite represents the background and the area that is clickable
     * */
    public Button(BitmapFont font, String text, Sprite sprite) {
        this.font = font;
        this.text = text;
        layout = new GlyphLayout(font, text);
        this.sprite = sprite;
    }

    /**
     *
     * @param mousePos
     */
    public void Hover(Vector2 mousePos){
        if (mousePos == null) return;
        if (mousePos.x >  sprite.getX() - (sprite.getWidth() * (sprite.getScaleX()-1)/2) && mousePos.x < sprite.getX() + (sprite.getWidth() * (sprite.getScaleX()+1)/2)){

            if (mousePos.y >  sprite.getY() - (sprite.getHeight() * (sprite.getScaleY()-1)/2) && mousePos.y < sprite.getY() + (sprite.getHeight() * (sprite.getScaleY()+1)/2)){
                hovering = true;
            } else hovering = false;
        } else hovering = false;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.setColor(255,255,255,255);
        if (hovering) sprite.setColor(0,0,0,128);
        sprite.draw(batch);
        font.setColor(new Color(255,255,255,255));
        font.draw(batch, text, position.x, position.y);

    }

    @Override
    public void SetPosition(float x, float y) {
        position = new Vector2(x - layout.width / 2,y+ layout.height / 2);
        sprite.setPosition(x - sprite.getWidth()/2,y - sprite.getHeight()/2);
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

    /**
     * Calls Button.Hover, and if left clicked runs Button.Click which runs action.Action() if hovering is true
     * @param mousePos The x, y position of the mouse cursor
     */
    @Override
    public void MouseUpdate(Vector2 mousePos) {
        Hover(mousePos);

        // Run Click if mouse is clicked
        if (Gdx.input.isButtonJustPressed(com.badlogic.gdx.Input.Buttons.LEFT)) { Click(); }
    }

    // Read Clicks on the screen and trigger action if hovering over button
    public void Click() {
        if (hovering) {
            action.Action();
        }
    }

    public void ChangeAction(ButtonAction action){
        this.action = action;
    }
    public void SetScale(float x, float y){
        sprite.setScale(x,y);
    }
}
