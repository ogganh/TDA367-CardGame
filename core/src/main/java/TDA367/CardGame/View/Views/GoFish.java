package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.CardHand;
import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GoFish implements GoFishInterface {
    // Specifically for go fish
    CardHand cardHand = new CardHand();
    List<OpponentHand> opponentHands = new ArrayList<>();

    Column buttons;
    Button btn;

    Sprite deck;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Texture atlas = ViewInformation.cardAtlas;
    Texture deckOfCardsAtlas = ViewInformation.deckOfCardsAtlas;


    Vector2 mousePosition = new Vector2(0, 0);

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
        //outline = new Sprite(atlas, 624, 64, 64, 80); // Outline for the selected card

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
                if (cardHand.GetSelectIndex() > -1) {
                    controller.handleAction((state.GetCurrentPlayer() + 1) % state.getPlayers().size(), "",
                            conversion.IntToRank(cardHand.GetSelectedCard()), conversion.IntToSuit(cardHand.GetSelectedCard()));
                }
            }
        });
        btn.SetScale(5, 3);

        buttons.AddUIElement(btn);

        for (int i = 0; i < state.getPlayers().size() -1; i++) {
            opponentHands.add(new OpponentHand());
        }

    }

    @Override
    public void Update() {

        //update player hand
        cardHand.ResetHand();

        int size = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().size();
        for (int i = 0; i < size; i++) {
            String rank = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getRank();
            String suit = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getSuit();
            cardHand.AddCard(conversion.CardToInt(suit, rank));
        }

        cardHand.Update(mousePosition);


        // Update opponents hands
        opponentHands.get(0).ResetHand();

        opponentHands.get(0).Update(state.getPlayers().get((state.GetCurrentPlayer() + 1) % 2).get_hand().size());
    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
        buttons.MouseUpdate(mousePosition);
        if (Gdx.input.isButtonJustPressed(com.badlogic.gdx.Input.Buttons.LEFT)) cardHand.SelectCard();
    }

    @Override
    public void Draw(SpriteBatch batch) {
        cardHand.Draw(batch);
        for (int i = 0; i < opponentHands.size(); i++) {
            opponentHands.get(i).Draw(batch);
        }
        deck.draw(batch);
        buttons.Draw(batch);
    }
}
