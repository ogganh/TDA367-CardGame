package TDA367.CardGame.View.Views.Games;

import TDA367.CardGame.View.UI.cards.CardHand;
import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.UI.cards.OpponentHand;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoFish implements ViewInterface {
    // Specifically for go fish
    CardHand cardHand = new CardHand();
    List<OpponentHand> opponentHands = new ArrayList<>();
    List<Sprite> thePond = new ArrayList<>();

    Column buttons;
    Button btn;

    float screenWidth = ViewInformation.screenSize.x;
    float screenHeight = ViewInformation.screenSize.y;

    Vector2 mousePosition = new Vector2(0, 0);

    GameState state;
    GameController controller;
    CardConversion conversion;

    // Temp ljud test
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pickupCard.wav"));
    Sound bell = Gdx.audio.newSound(Gdx.files.internal("sounds/bell.wav"));


    public GoFish(GameState state, GameController controller) {
        this.state = state;
        this.controller = controller;
        conversion = new CardConversion();
    }

    @Override
    public void CreateView() {
        // Creates the pond
        Random rand = new Random();
        for (int i = 0; i < 52; i++) {
            thePond.add(new Sprite(ViewInformation.cardAtlas, 0, ViewInformation.cardHeight * 4, ViewInformation.cardWidth, ViewInformation.cardHeight));

            // Random position and rotation
            thePond.get(i).setPosition(rand.nextFloat(screenWidth / 3,2 *screenWidth/3),rand.nextFloat(screenHeight / 4,2 *screenHeight/4));
            thePond.get(i).setScale(0.5f);
            thePond.get(i).setRotation(rand.nextFloat(0f,180f));
        }

        // Creates column that the buttons will be in
        buttons = new Column(new Vector2(450, 50), 50);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) UpdateState();
        cardHand.Update(mousePosition);

    }
    int currentPlayer = -1;
    @Override
    public void UpdateState(){





        if (state.GetCurrentPlayer() != currentPlayer) {
            cardHand.NextPlayer();
        }

        // Temp ljud test
        if (state.GetCurrentPlayer() == currentPlayer) {
            bell.play();
        }

        //update player hand
        cardHand.ResetHand();
        int size = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().size();
        for (int i = 0; i < size; i++) {
            String rank = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getRank();
            String suit = state.getPlayers().get(state.GetCurrentPlayer()).get_hand().get(i).getSuit();
            cardHand.AddCard(conversion.CardToInt(suit, rank),
                new Vector2(ViewInformation.screenSize.x/2,ViewInformation.screenSize.y/2));
        }

        // Temp ljud test
        sound.play();


        currentPlayer = state.GetCurrentPlayer();

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

        for (int i = 0; i < thePond.size(); i++) {
            thePond.get(i).draw(batch);
        }
        for (int i = 0; i < opponentHands.size(); i++) {
            opponentHands.get(i).Draw(batch);
        }


        cardHand.Draw(batch);
        buttons.Draw(batch);
    }
}
