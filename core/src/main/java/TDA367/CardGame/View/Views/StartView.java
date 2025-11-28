package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.ViewController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    Button button;
    ViewController VC;

    Vector2 mousePosition;

    int test = 0;

    public StartView(BitmapFont font, ViewController VC) {
        this.font = font;
        this.VC = VC;
    }

    @Override
    public void CreateView() {
        button = new Button(font, "Start Game", 100,100);

        button.ChangeAction(new ButtonAction() {
            @Override
            public void Action(){
                VC.StartGoFish();
            }
        });

        button.SetPosition(screenWidth/2, screenHeight /2);





//        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2), 100);
//        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Start Game"));
//        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Join Game"));
//        buttons.AddUIElement(UIElementFactory.CreateButton(font, "Exit"));

//        Gdx.app.log( String.valueOf(test), "---------Player-1-----------");
//        test++;


//        for (int i = 0; i < buttons.size(); i++) {
//            buttons.get(i).SetPosition(screenWidth/2, screenHeight /2 - 30 * i + 30);
//        }
    }

    @Override
    public void Update() {
    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        //buttons.Draw(batch);
        button.Draw(batch);
    }

    public void MouseClick(){
        button.ClickCheck(mousePosition);
    }
}
