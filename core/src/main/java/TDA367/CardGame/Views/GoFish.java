package TDA367.CardGame.Views;

import TDA367.CardGame.UI.Card;
import TDA367.CardGame.UI.UIElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GoFish implements ViewInterface {
    // Specifically for go fish
    List<UIElement> cardHand = new ArrayList<>();
    List<Card> opponentHand = new ArrayList<>();
    Sprite deck;

    //TEMP
    float screenWidth = 495;
    float screenHeight = 270;
    int cardWidth = 48;
    int cardHeight = 64;
    Texture atlas;
    Texture deckOfCardsAtlas;
    float cardSpace = 10;
    float cardLift = 20;
    Boolean check = true;
    int selected;
    Vector2 mousePosition = new Vector2(0,0);
    float angle = 0;

    public GoFish() {
    }

    @Override
    public void CreateView() {
        atlas = new Texture("card_textures/1.2 Poker cards.png");
        deckOfCardsAtlas = new Texture("card_textures/Deck of cards ( full cards ).png");

        deck = new Sprite(deckOfCardsAtlas, 48, 64 ,49,80);
        deck.setPosition(screenWidth /2 - deck.getWidth()/2, screenHeight /2 - deck.getHeight()/2);

        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < 3; i++) {
                cardHand.add(new Card(new Sprite(atlas, cardWidth * i, 0 ,cardWidth, cardHeight)));

            }
        }
        for (int i = 0; i < 3; i++) {
            opponentHand.add(new Card(new Sprite(atlas, 0, cardHeight * 4 ,cardWidth, cardHeight)));
        }
    }
    @Override
    public void Update() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            cardHand.add(new Card(new Sprite(atlas, 0, 0 ,48,64)));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            if (!cardHand.isEmpty()){
                cardHand.remove(cardHand.size()-1);

            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && check){
            angle += 10;
            check = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && check){
            angle -= 10;
            check = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&  !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            check = true;
        }

        selected = MathUtils.clamp(selected,0,cardHand.size() -1);


        selected = -1;

        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXPosition(cardHand.size(), i);

            if (mousePosition.y < 64) {
                if (i == cardHand.size()-1) {
                    if (mousePosition.x > xPos && mousePosition.x < xPos + cardHand.get(i).GetSize().x) {
                        selected = i;
                    }
                }
                else {
                    if (mousePosition.x > xPos && mousePosition.x < CardsXPosition(cardHand.size(), i +1)) {
                        selected = i;
                    }
                }
            }


            if (i == selected){
                cardHand.get(i).SetPosition(
                    xPos,
                    cardLift);
            }
            else {
                cardHand.get(i).SetPosition(
                    xPos,
                    0);
            }

        }
        for (int i = 0; i < opponentHand.size(); i++) {
            float xPos = CardsXPosition(opponentHand.size(), i);
            Vector2 pos = opponentHand.get(i).GetPosition();


            opponentHand.get(i).SetOrigin(-pos.x + screenWidth/2, -pos.y + screenHeight/2 );
            opponentHand.get(i).SetRotation(angle);
            opponentHand.get(i).SetPosition(xPos, screenHeight - opponentHand.get(i).GetSize().y);
        }

    }
    float CardsXPosition(int amountOfCards, int index){
        float maxMargin = screenWidth / (cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, screenWidth);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((screenWidth/2) - (handWidth /2) + margin * index,0 , screenWidth -cardWidth);
    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;

    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < cardHand.size(); i++) {
            cardHand.get(i).Draw(batch);
        }
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).Draw(batch);
        }
        deck.draw(batch);
    }
}
