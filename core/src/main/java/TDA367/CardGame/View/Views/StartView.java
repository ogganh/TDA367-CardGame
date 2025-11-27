package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StartView implements ViewInterface{

    Column buttons;
    //List<UIElement> buttons = new ArrayList<>();
    BitmapFont font;

    //TEMP
    float screenWidth = 495;
    float screenHeight = 270;


    public StartView(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void CreateView() {
        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2), 100);
        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Start Game"));
        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Join Game"));
        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Exit"));



//        for (int i = 0; i < buttons.size(); i++) {
//            buttons.get(i).SetPosition(screenWidth/2, screenHeight /2 - 30 * i + 30);
//        }
    }

    @Override
    public void Update() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {

    }

    @Override
    public void Draw(SpriteBatch batch) {
        buttons.Draw(batch);
    }
}
