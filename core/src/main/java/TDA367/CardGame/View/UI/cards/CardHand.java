package TDA367.CardGame.View.UI.cards;

import TDA367.CardGame.View.SoundManager;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    List<Card> cardHand = new ArrayList<>();
    List<Card> oldHand = new ArrayList<>();

    int lastHovered;
    int hovered;
    int selected;
    //Vector2 mousePosition = new Vector2(0, 0);
    Texture atlas = ViewInformation.cardAtlas;
    Sprite outline = new Sprite(atlas, 624, 64, 64, 80);


    public CardHand(){
        outline.setScale(ViewInformation.cardScale);
    }
    /**
     * Logic loop, runs every frame.
     * <br></br>
     * Updates the player and opponents hands to reflect their current states.
     * <br></br>
     * "Raises" cards that are hovered over
     * <br></br>
     * Places the opponents hand
     */
    public void update(Vector2 mousePosition){
        hovered = -1;
        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXPosition(cardHand.size(), i);

            if (mousePosition.y < ViewInformation.cardHeight) {
                if (i == cardHand.size() - 1) {
                    if (mousePosition.x > xPos && mousePosition.x < xPos + cardHand.get(i).getSize().x) {
                        hovered = i;
                    }
                } else {
                    if (mousePosition.x > xPos && mousePosition.x < CardsXPosition(cardHand.size(), i + 1)) {
                        hovered = i;
                    }
                }
            }

            if (i == hovered) {
                cardHand.get(i).lerpPosition(
                    xPos,
                    ViewInformation.cardLift + ViewInformation.cardYPos
                );

                // Spela ljudet bara om vi just började hovera detta kort
                if (hovered != lastHovered) {
                    SoundManager.playHover();
                    lastHovered = hovered;
                }

            } else {
                cardHand.get(i).lerpPosition(
                    xPos, ViewInformation.cardYPos
                );
            }
        }

        // Om inget kort är hovered, nollställ lastHovered
        if (hovered == -1) {
            lastHovered = -1;
        }

    }
    /**
     * Draws the cards to the screen.
     * */
    public void draw(SpriteBatch batch){
        for (int i = 0; i < cardHand.size(); i++) {
            if (selected == i) {
                outline.setPosition(cardHand.get(i).getPosition().x - 8, cardHand.get(i).getPosition().y - 8);
                outline.draw(batch);
            }
            cardHand.get(i).draw(batch);
        }
    }
    /**
     * Adds card to the hand,
     * @param index The index of the card in texture.
     * @param startPosition The postition the card will appear from.
     * */
    public void addCard(int index, Vector2 startPosition) {

        // Check if any cards have been removed
        for (Card card : oldHand) {
            if (card.getIndex() == index) {
                cardHand.add(card);
                return;
            }
        }

        int y = index / 13;
        int x = index % 13;
        Card card = new Card(new Sprite(atlas, x * 48, y * 64, 48, 64), index);
        card.setPosition(startPosition.x, startPosition.y);
        cardHand.add(card);

    }
    public void resetHand() {
        oldHand.addAll(cardHand);
        cardHand.clear();
    }
    public void nextPlayer() {
        oldHand.clear();
        cardHand.clear();
    }
    public int getSelectIndex(){
        return selected;
    }
    public int getSelectedCard() {
        return cardHand.get(selected).getIndex();
    }

    /**
     * Selects the currently hovered card
     */
    public void selectCard(){
        selected = hovered;
        SoundManager.playSelect();
    }

    public List<Card> getCards(){
        return cardHand;
    }

    private float CardsXPosition(int amountOfCards, int index) {
        float maxMargin = ViewInformation.screenSize.x / (ViewInformation.cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, ViewInformation.screenSize.x);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((ViewInformation.screenSize.x / 2) - (handWidth / 2) + margin * index, 0, ViewInformation.screenSize.x - ViewInformation.cardWidth);
    }

//    /**
//     * @param index the index of which the card will be placed
//     * @param card the card that is inserted
//     * */
//    private void InsertCard(int index, Card card){
//        // flytta all kort från index höger, sedan placera in card
//        Card temp;
//        temp = cardHand.get(index +1);
//        cardHand.set(index +1, cardHand.get(index));
//        for (int i = index; i < cardHand.size()-1; i++) {
//            temp = cardHand.get(i +1);
//            cardHand.set(i +1, cardHand.get(i));
//        }
//
//        cardHand.remove(card);
//    }
}