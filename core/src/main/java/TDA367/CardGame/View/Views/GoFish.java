package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.Card;
import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.UI.UIElementFactory;
import TDA367.CardGame.controller.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoFish implements GoFishInterface {
    // Specifically for go fish
    List<Card> cardHand = new ArrayList<>();
    Sprite outline;
    List<UIElement> opponentHand = new ArrayList<>();
    List<Integer> opponentHands = new ArrayList<>();
    Sprite deck;

    //TEMP
    float screenWidth = 495;
    float screenHeight = 270;
    int cardWidth = 48;
    int cardHeight = 64;
    Texture atlas;
    Texture deckOfCardsAtlas;

    // skicka dessa till en settings klass
    float cardSpace = 10;
    float cardLift = 20;
    float cardYPos = -10;


    Boolean check = true;
    int hovered;
    int selected;
    Vector2 mousePosition = new Vector2(0,0);
    float angle = 0;


    GameState state;
     //fishController
     CardConversion conversion;

    // TODO: gör en ny handklass som hanterar logiken med att flytta kort osv

    public GoFish(GameState state) {
        this.state = state;
        conversion = new CardConversion();
    }

    @Override
    public void CreateView() {

        atlas = new Texture("card_textures/1.2 Poker cards.png");
        deckOfCardsAtlas = new Texture("card_textures/Deck of cards ( full cards ).png");

        outline = new Sprite(atlas,624, 64, 64,80);
        deck = new Sprite(deckOfCardsAtlas, 48, 64 ,49,80);
        deck.setPosition(screenWidth /2 - deck.getWidth()/2, screenHeight /2 - deck.getHeight()/2);

        for (int i = 0; i < 3; i++) {
            opponentHand.add(UIElementFactory.CreateCard(new Sprite(atlas, 0, cardHeight * 4 ,cardWidth, cardHeight), -1));
        }
    }
    int temp = 0;
    @Override
    public void Update() {
        ResetHand();

        int size = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().size();

        for (int i = 0; i < size; i++) {
            String rank = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getRank();
            String suit = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getSuit();
            AddCard(conversion.CardToInt(suit,rank));
        }

        //TempInput();

        //hovered = MathUtils.clamp(hovered,0,cardHand.size() -1);


        hovered = -1;

        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXPosition(cardHand.size(), i);

            if (mousePosition.y < 64) {
                if (i == cardHand.size()-1) {
                    if (mousePosition.x > xPos && mousePosition.x < xPos + cardHand.get(i).GetSize().x) {
                        hovered = i;
                    }
                }
                else {
                    if (mousePosition.x > xPos && mousePosition.x < CardsXPosition(cardHand.size(), i +1)) {
                        hovered = i;
                    }
                }
            }


            if (i == hovered){
                cardHand.get(i).SetPosition(
                    xPos,
                    cardLift + cardYPos);
            }
            else {
                cardHand.get(i).SetPosition(
                    xPos, cardYPos
                );
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

    private void TempInput(){
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            if(hovered != -1){
                SelectCard();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            Random rand = new Random();
            AddCard(rand.nextInt(0, 51));
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
        if (selected == -1){
            //deck.setPosition(0,deck.getY());
            //outline.setPosition(0,deck.getY());
        }
        for (int i = 0; i < cardHand.size(); i++) {
            if (selected == i){
                outline.setPosition(cardHand.get(i).GetPosition().x - 8,cardHand.get(i).GetPosition().y -8);
                outline.draw(batch);
            }
            cardHand.get(i).Draw(batch);
        }
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).Draw(batch);
        }
        deck.draw(batch);
    }
    @Override
    public void AddCards(List<Integer> cards){
        for (int i = 0; i < cards.size(); i++) {
            AddCard(cards.get(i));
        }
    }
    @Override
    public void AddCard(int index){
        int y = index / 13;
        int x = index % 13;

        cardHand.add(new Card(new Sprite(atlas, x * 48, y * 64 ,48,64), index));
    }
    @Override
    public void ResetHand(){
        cardHand.clear();
    }
    @Override
    public void SetOpponentscards(List<Integer> cards){
        for (int i = 0; i < cards.size(); i++) {
            SetOpponentCard(cards.get(i), i);
        }
    }

    public void SetOpponentCard(int amountOfCards, int opponent){
        opponentHands.set(opponent, amountOfCards);
    }
    @Override
    public void SelectCard(){
        selected = hovered;
    }
    @Override
    public int GetSelectedCard(){
        return  cardHand.get(selected).GetIndex();
    }
}
