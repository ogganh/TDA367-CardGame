package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.controller.ViewController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class StartView implements ViewInterface{

    Column buttons;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Texture atlas;
    ViewController view;

    public Vector2 mousePosition;

    Button btn;

    public StartView(ViewController view) {
        this.view = view;
    }

    @Override
    public void CreateView() {
        atlas = new Texture("CardGameUI.png");
        ViewInformation.font.getData().setScale(0.5f);
        mousePosition = new Vector2(0,0);

        buttons = new Column(new Vector2(screenWidth/2, screenHeight /2), 50);
        btn = new Button(ViewInformation.font,
            "Start Game",
                new Sprite(atlas, 32, 0 ,16,16));
        btn.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                // Byt view
                // viewController
                view.SetGoFish();
            }
        });
        btn.SetScale(8,3);
        buttons.AddUIElement(btn);
    }

    @Override
    public void Update() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
        btn.MouseUpdate(mousePosition);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        buttons.Draw(batch);
    }

    @Override
    public void Click() {
        btn.ClickCheck(mousePosition);
    }
}
