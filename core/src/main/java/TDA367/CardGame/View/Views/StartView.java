package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.Button;
import TDA367.CardGame.View.UI.UIElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class StartView implements ViewInterface{

    List<Button> buttons = new ArrayList<>();
    BitmapFont font;

    //TEMP
    float screenWidth = 495;
    float screenHeight = 270;

    public StartView(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void CreateView() {
        buttons.add(new Button(font, "Start Game"));
        buttons.add(new Button(font, "Join Game"));
        buttons.add(new Button(font, "Exit"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).SetPosition(screenWidth/2, screenHeight /2 - 30 * i + 30);
        }
    }

    @Override
    public void Update() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {

    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).Draw(batch);
        }
    }
}
