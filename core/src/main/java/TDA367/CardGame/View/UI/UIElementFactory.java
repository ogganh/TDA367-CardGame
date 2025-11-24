package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class UIElementFactory {
    public static UIElement CreateButton(BitmapFont font, String text){
        return new Button(font, text);
    }
    public static UIElement CreateCard(Sprite sprite, int index){
        return new Card(sprite, index);
    }
}