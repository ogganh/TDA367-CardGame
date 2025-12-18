package TDA367.CardGame.View.UI;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class TextInputField implements UIElement{
    private BitmapFont font;
    private String text;
    private Vector2 position;
    private GlyphLayout layout;

    private boolean Selected = false;
    private int MaxChars;
    protected Sprite sprite;
    private boolean hovering = false;
    
    int size = 10;

    /**
     * Creates Text
     * */
    public TextInputField(BitmapFont font, String text, int MaxChars, Sprite sprite) {
        this.text = text;
        this.font = font;
        this.position = new Vector2(0,0);
        this.MaxChars = MaxChars;
        this.sprite = sprite;
        layout = new GlyphLayout(font, text);
    }

    public void setText(String text){
        this.text = text;
    }
    public String GetText(){
        return text;
    }

    @Override
    public void draw(SpriteBatch batch) {

        sprite.setColor(1, 1, 1, 1);
        if (hovering)
            sprite.setColor(0.5f, 0.5f, 0.5f, 1);
        sprite.draw(batch);

        if (Selected) {
            font.setColor(new Color(0,0,0,255));
        }
        else font.setColor(new Color(255,255,255,255));
        
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public void setPosition(float x, float y) {
        position = new Vector2(x - layout.width / 2, y + layout.height / 2);
        sprite.setPosition(x + sprite.getWidth() /4, y - sprite.getHeight() / 2);
    }

    @Override
    public Vector2 getPosition() {
        return position;
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
        hover(mousePos);

        // Run Click if mouse is clicked
        if (Gdx.input.isButtonJustPressed(com.badlogic.gdx.Input.Buttons.LEFT)) {
            if (hovering) {
                Selected = true;
            }
            else Selected = false;
        }
        if (Selected) {
            TextInput();
        }
    }
    private void TextInput(){
        for (int i = 7; i < 16; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (text.length() < MaxChars) {
                    text += Input.Keys.toString(i);
                }else{
                    text = removeChars(text,1);
                    text += Input.Keys.toString(i);
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) {
            if (text.length() < MaxChars) {
                    text += Input.Keys.toString(Input.Keys.PERIOD);
            }else{
                text = removeChars(text,1);
                text += Input.Keys.toString(Input.Keys.PERIOD);
            }
        }
    
        if (Gdx.input.isKeyJustPressed(Input.Keys.DEL)) {
            
            text = removeChars(text,1);
        }
    }

    private String removeChars(String str, int numberOfCharactersToRemove) {
        if(str != null && !str.trim().isEmpty()) {
            return str.substring(0, str.length() - numberOfCharactersToRemove);
        }
        return "";
    }
    public void hover(Vector2 mousePos) {
        if (mousePos == null)
            return;
        if (mousePos.x > sprite.getX() - (sprite.getWidth() * (sprite.getScaleX() - 1) / 2)
                && mousePos.x < sprite.getX() + (sprite.getWidth() * (sprite.getScaleX() + 1) / 2)) {

            if (mousePos.y > sprite.getY() - (sprite.getHeight() * (sprite.getScaleY() - 1) / 2)
                    && mousePos.y < sprite.getY() + (sprite.getHeight() * (sprite.getScaleY() + 1) / 2)) {
                hovering = true;
            } else
                hovering = false;
        } else
            hovering = false;
    }
    public void setScale(float x, float y) {
        sprite.setScale(x, y);
    }

    @Override
    public void addUIElement(UIElement element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUIElement'");
    }
}
