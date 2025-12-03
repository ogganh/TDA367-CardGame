package TDA367.CardGame.View.UI;

import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.tool.View;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    List<Card> cardHand = new ArrayList<>();
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
    public void Update(Vector2 mousePosition){
        hovered = -1;
        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXPosition(cardHand.size(), i);
            if (mousePosition.y < ViewInformation.cardHeight) {
                if (i == cardHand.size() - 1) {
                    if (mousePosition.x > xPos && mousePosition.x < xPos + cardHand.get(i).GetSize().x) {
                        hovered = i;
                    }
                } else {
                    if (mousePosition.x > xPos && mousePosition.x < CardsXPosition(cardHand.size(), i + 1)) {
                        hovered = i;
                    }
                }
            }

            if (i == hovered) {
                cardHand.get(i).SetPosition(
                    xPos,
                    ViewInformation.cardLift + ViewInformation.cardYPos);
            } else {
                cardHand.get(i).SetPosition(
                    xPos, ViewInformation.cardYPos);
            }

        }
    }

    public void InsertCard(int index, Card card){

    }
    public void RemoveCard(int index){

    }

    public void Draw(SpriteBatch batch){
        for (int i = 0; i < cardHand.size(); i++) {
            if (selected == i) {
                outline.setPosition(cardHand.get(i).GetPosition().x - 8, cardHand.get(i).GetPosition().y - 8);
                outline.draw(batch);
            }
            cardHand.get(i).Draw(batch);
        }
    }
    public void AddCard(int index) {
        int y = index / 13;
        int x = index % 13;

        cardHand.add(new Card(new Sprite(atlas, x * 48, y * 64, 48, 64), index));
    }
    public void AddCards(List<Integer> cards) {
        for (int i = 0; i < cards.size(); i++) {
            AddCard(cards.get(i));
        }
    }

    public void ResetHand() {
        cardHand.clear();
    }
    public int GetSelectIndex(){
        return selected;
    }
    public int GetSelectedCard() {
        return cardHand.get(selected).GetIndex();
    }

    /**
     * Selects the currently hovered card
     */
    public void SelectCard(){
        selected = hovered;
    }

    public List<Card> GetCards(){
        return cardHand;
    }

    float CardsXPosition(int amountOfCards, int index) {
        float maxMargin = ViewInformation.screenSize.x / (ViewInformation.cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, ViewInformation.screenSize.x);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((ViewInformation.screenSize.x / 2) - (handWidth / 2) + margin * index, 0, ViewInformation.screenSize.x - ViewInformation.cardWidth);
    }
}
