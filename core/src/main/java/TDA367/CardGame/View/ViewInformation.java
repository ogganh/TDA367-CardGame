package TDA367.CardGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;


public class ViewInformation {
    public static Vector2 screenSize = new Vector2(495, 270);
    public static int cardWidth = 48;
    public static int cardHeight = 64;
    public static float cardScale = 1f;
    public static float lerpSpeed = 5f;
    public static float delaySeconds = 0.75f;


    public static float cardSpace = 20;
    public static float cardLift = 20;
    public static float cardYPos = -30;
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), false);
    public static Texture cardAtlas = new Texture("card_textures/1.2 Poker cards.png");
    public static Texture deckOfCardsAtlas = new Texture("card_textures/Deck of cards ( full cards ).png");
    public static Texture uiAtlas = new Texture("CardGameUI.png");
    public static Texture greenButtonTexture = new Texture("textures/green_button.png");
}
