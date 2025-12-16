package TDA367.CardGame.View.UI;

import TDA367.CardGame.View.UI.cards.Card;
import TDA367.CardGame.View.UI.widgets.Button;
import TDA367.CardGame.View.UI.widgets.Column;
import TDA367.CardGame.View.UI.widgets.Row;
import TDA367.CardGame.View.UI.widgets.Text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElementFactory {
    public static UIElement createText(BitmapFont font, String text){
        return new Text(font, text);
    }
    public static UIElement createCard(Sprite sprite, int index){
        return new Card(sprite, index);
    }
    public static UIElement createRow(Vector2 position, int width){
        return new Row(position,width);
    }
    public static UIElement createColumn(Vector2 position, int height){
        return new Column(position,height);
    }
    public static UIElement createButton(BitmapFont font, String text, Sprite sprite, ButtonAction action){
        return new Button(font, text, sprite, action);
    }
}
