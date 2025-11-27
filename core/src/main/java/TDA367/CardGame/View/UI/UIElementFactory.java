package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElementFactory {
    public static UIElement CreateButton(BitmapFont font, String text){
        return new Button(font, text);
    }
    public static UIElement CreateText(BitmapFont font, String text){
        return new Text(font, text);
    }
    public static UIElement CreateCard(Sprite sprite, int index){
        return new Card(sprite, index);
    }
    public static UIElement CreateRow(Vector2 position, int width){
        return new Row(position,width);
    }
    public static UIElement CreateColumn(Vector2 position, int height){
        return new Column(position,height);
    }
}
