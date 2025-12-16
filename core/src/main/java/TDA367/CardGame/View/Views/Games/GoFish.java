package TDA367.CardGame.View.Views.Games;

import TDA367.CardGame.View.UI.cards.CardHand;
import TDA367.CardGame.View.UI.*;
import TDA367.CardGame.View.UI.cards.OpponentHand;
import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.MainView;
import TDA367.CardGame.View.Views.ViewController;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoFish implements ViewInterface {
    // Specifically for go fish
    private CardHand cardHand = new CardHand();
    private List<OpponentHand> opponentHands = new ArrayList<>();
    private List<Sprite> thePond = new ArrayList<>();
    private Texture background;
    private Column buttons;
    private Button guessButton;

    private Button rules;

    // float screenWidth = ViewInformation.screenSize.x;
    // float screenHeight = ViewInformation.screenSize.y;

    private Vector2 mousePosition = new Vector2(0, 0);

    private GameState state;
    private GameController controller;
    private ViewController mainView;
    private CardConversion conversion;

    // Temp ljud test
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pickupCard.wav"));
    private Sound bell = Gdx.audio.newSound(Gdx.files.internal("sounds/bell.wav"));

    public GoFish(GameState state, GameController controller, ViewController mainView) {
        this.state = state;
        this.controller = controller;
        this.mainView = mainView;
        conversion = new CardConversion();
    }

    @Override
    public void createView() {
        background = new Texture("textures/background.png");

        // Creates the pond
        Random rand = new Random();
        for (int i = 0; i < 52; i++) {
            thePond.add(new Sprite(ViewInformation.cardAtlas, 0, ViewInformation.cardHeight * 4,
                    ViewInformation.cardWidth, ViewInformation.cardHeight));

            // Random position and rotation
            thePond.get(i).setPosition(
                    rand.nextFloat(ViewInformation.screenSize.x / 3, 2 * ViewInformation.screenSize.x / 3),
                    rand.nextFloat(ViewInformation.screenSize.y / 4, 2 * ViewInformation.screenSize.y / 4));
            thePond.get(i).setScale(0.5f);
            thePond.get(i).setRotation(rand.nextFloat(0f, 180f));
        }

        // Creates column that the buttons will be in
        buttons = new Column(new Vector2(450, 50), 50);
        // Create Guess button
        guessButton = new GreenButton(
                ViewInformation.font,
                "Guess");
                
        // Add a "on click" function to the guess button
        guessButton.changeAction(new ButtonAction() {
            @Override
            public void action() {
                // Send the input to the controller if a card is selected
                if (cardHand.getSelectIndex() > -1) {
                    controller.handleAction((state.getCurrentPlayer() + 1) % state.getPlayers().size(), "",
                            conversion.intToRank(cardHand.getSelectedCard()),
                            conversion.intToSuit(cardHand.getSelectedCard()));
                }
            }
        });

        guessButton.setScale(5, 3);
        buttons.addUIElement(guessButton);

        // Create rules button
        rules = new Button(
                ViewInformation.font,
                "",
                new Sprite(new Texture("textures/rule_book.png"), 0, 0, 480, 480));

        // Add a "on click" function to the guess button
        rules.changeAction(new ButtonAction() {
            @Override
            public void action() {
                mainView.rules();
            }
        });
        rules.setPosition(100, ViewInformation.screenSize.y - 40);
        rules.setScale(0.1f, 0.1f);

        for (int i = 0; i < state.getPlayers().size() - 1; i++) {
            opponentHands.add(new OpponentHand());
        }
    }

    @Override
    public void update() {
        // if (Gdx.input.isKeyJustPressed(Input.Keys.G)) mainView.EndScreen();
        cardHand.update(mousePosition);
        if (state.isMiddleScreenOpen()) {
            mainView.middleScreen();
        }
    }

    int currentPlayer = -1;

    @Override
    public void updateState() {
        if (state.getCurrentPlayer() != currentPlayer) {
            cardHand.nextPlayer();
        }

        // Temp ljud test
        if (state.getCurrentPlayer() == currentPlayer) {
            bell.play();
        }

        // update player hand
        cardHand.resetHand();

        // TODO: Viewn ska inte ha kontakt med UserPlayer
        int size = state.getPlayers().get(state.getCurrentPlayer()).getHand().size();
        for (int i = 0; i < size; i++) {
            String rank = state.getPlayers().get(state.getCurrentPlayer()).getHand().get(i).getRank();
            String suit = state.getPlayers().get(state.getCurrentPlayer()).getHand().get(i).getSuit();
            cardHand.addCard(conversion.cardToInt(suit, rank),
                    new Vector2(ViewInformation.screenSize.x / 2, ViewInformation.screenSize.y / 2));
        }

        // Temp ljud test
        sound.play();

        currentPlayer = state.getCurrentPlayer();

        // Update opponents hands
        opponentHands.get(0).resetHand();

        opponentHands.get(0).update(state.getPlayers().get((state.getCurrentPlayer() + 1) % 2).getHand().size());
    }

    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
        buttons.mouseUpdate(mousePosition);
        if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT))
            cardHand.selectCard();
        rules.mouseUpdate(mousePosition);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, 0, ViewInformation.screenSize.x, ViewInformation.screenSize.y);

        for (int i = 0; i < thePond.size(); i++) {
            thePond.get(i).draw(batch);
        }
        for (int i = 0; i < opponentHands.size(); i++) {
            opponentHands.get(i).draw(batch);
        }

        cardHand.draw(batch);

        buttons.draw(batch);
        rules.draw(batch);

        int CurrentIndex = state.getCurrentPlayer(); // hämtar index för nuvarande spelare från game state
        int OpponentIndex = (CurrentIndex + 1) % 2; // hämtar index för motståndaren

        String CurrentPlayerText = "Player " + (CurrentIndex + 1); // text för att visa nuvarande spelare
        String OpponentPlayerText = "Player " + (OpponentIndex + 1); // text för att visa motståndaren

        int currentBooks = state.getBookCount(CurrentIndex); // hämtar antal böcker för nuvarande spelare
        int opponentBooks = state.getBookCount(OpponentIndex); // hämtar antal böcker för motståndaren

        String currentBooksText = "Books: " + currentBooks; // text för att visa antal böcker för spelare har
        String opponentBooksText = "Books: " + opponentBooks;

        ViewInformation.font.draw(batch, CurrentPlayerText, 10, 40); // ritar texten för spelare
        ViewInformation.font.draw(batch, currentBooksText, 10, 20); // ritar texten för antal böcker

        ViewInformation.font.draw(batch, OpponentPlayerText, 10, ViewInformation.screenSize.y - 20);
        ViewInformation.font.draw(batch, opponentBooksText, 10, ViewInformation.screenSize.y - 40);
    }
}
