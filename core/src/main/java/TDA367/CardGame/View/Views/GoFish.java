package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GoFish implements GoFishInterface {
    // Specifically for go fish
    List<Card> cardHand = new ArrayList<>();
    Sprite outline;
    List<UIElement> opponentHand = new ArrayList<>();
    List<Integer> opponentHands = new ArrayList<>();

    Column buttons;
    Button btn;

    Sprite deck;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    int cardWidth = ViewInformation.cardWidth;
    int cardHeight = ViewInformation.cardHeight;

    Texture atlas = ViewInformation.cardAtlas;
    Texture deckOfCardsAtlas = ViewInformation.deckOfCardsAtlas;

    Boolean check = true;
    int hovered;
    int selected;
    Vector2 mousePosition = new Vector2(0, 0);
    float angle = 0;

    GameState state;
    GameController controller;
    // fishController
    CardConversion conversion;

    // TODO: gör en ny handklass som hanterar logiken med att flytta kort osv

    public GoFish(GameState state, GameController controller) {
        this.state = state;
        this.controller = controller;
        conversion = new CardConversion();
    }

    @Override
    public void CreateView() {
        outline = new Sprite(atlas, 624, 64, 64, 80); // Outline for the selected card
        deck = new Sprite(deckOfCardsAtlas, 48, 64, 49, 80); // "Pond" sprite
        deck.setPosition(screenWidth / 2 - deck.getWidth() / 2, screenHeight / 2 - deck.getHeight() / 2);

        buttons = new Column(new Vector2(400, 50), 50);

        // Create Guess button
        btn = new Button(
                ViewInformation.font,
                "Guess",
                new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));
        // Add a "on click" function to the guess button
        btn.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                // Send the input to the controller if a card is selected
                if (selected > -1) {
                    controller.handleAction((state.GetCurrentPlayer() + 1) % state.getPlayers().size(), "",
                            conversion.IntToRank(GetSelectedCard()), conversion.IntToSuit(GetSelectedCard()));
                }
            }
        });
        btn.SetScale(5, 3);

        buttons.AddUIElement(btn);

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
    @Override
    public void Update() {
        ResetHand();

        int size = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().size();
        for (int i = 0; i < size; i++) {
            String rank = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getRank();
            String suit = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getSuit();
            AddCard(conversion.CardToInt(suit, rank));
        }

        opponentHand.clear();
        for (int i = 0; i < state.getPlayers().get((state.GetCurrentPlayer() + 1) % 2).get_hand().size(); i++) {
            opponentHand.add(UIElementFactory.CreateCard(new Sprite(atlas, 0, cardHeight * 4, cardWidth, cardHeight), -1));
        }

        hovered = -1;
        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXPosition(cardHand.size(), i);
            if (mousePosition.y < 64) {
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

        for (int i = 0; i < opponentHand.size(); i++) {
            float xPos = CardsXPosition(opponentHand.size(), i);
            Vector2 pos = opponentHand.get(i).GetPosition();

            opponentHand.get(i).SetOrigin(-pos.x + screenWidth / 2, -pos.y + screenHeight / 2);
            opponentHand.get(i).SetRotation(angle);
            opponentHand.get(i).SetPosition(xPos, screenHeight - opponentHand.get(i).GetSize().y);
        }
    }

    float CardsXPosition(int amountOfCards, int index) {
        float maxMargin = screenWidth / (ViewInformation.cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, screenWidth);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((screenWidth / 2) - (handWidth / 2) + margin * index, 0, screenWidth - cardWidth);
    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
        buttons.MouseUpdate(mousePosition);
        if (Gdx.input.isButtonJustPressed(com.badlogic.gdx.Input.Buttons.LEFT)) SelectCard();
    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < cardHand.size(); i++) {
            if (selected == i) {
                outline.setPosition(cardHand.get(i).GetPosition().x - 8, cardHand.get(i).GetPosition().y - 8);
                outline.draw(batch);
            }
            cardHand.get(i).Draw(batch);
        }
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).Draw(batch);
        }
        deck.draw(batch);
        buttons.Draw(batch);
    }

    @Override
    public void AddCards(List<Integer> cards) {
        for (int i = 0; i < cards.size(); i++) {
            AddCard(cards.get(i));
        }
    }

    @Override
    public void AddCard(int index) {
        int y = index / 13;
        int x = index % 13;

        cardHand.add(new Card(new Sprite(atlas, x * 48, y * 64, 48, 64), index));
    }

    @Override
    public void ResetHand() {
        cardHand.clear();
    }

    @Override
    public void SetOpponentscards(List<Integer> cards) {
        for (int i = 0; i < cards.size(); i++) {
            SetOpponentCard(cards.get(i), i);
        }
    }

    public void SetOpponentCard(int amountOfCards, int opponent) {
        opponentHands.set(opponent, amountOfCards);
    }

    /**
     * Selects the currently hovered card
     */
    @Override
    public void SelectCard() {
        selected = hovered;
    }

    @Override
    public int GetSelectedCard() {
        return cardHand.get(selected).GetIndex();
    }
}
