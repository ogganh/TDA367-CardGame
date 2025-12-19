package TDA367.CardGame.View.Views.Games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import TDA367.CardGame.View.ViewInformation;
import TDA367.CardGame.View.UI.ButtonAction;
import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.UI.UIElementFactory;
import TDA367.CardGame.View.UI.cards.CardHand;
import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.ViewController;
import TDA367.CardGame.View.Views.ViewInterface;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.card_logic.Rank;
import TDA367.CardGame.model.card_logic.Suit;

public class Plump implements ViewInterface {
    private GameState state;
    private GameController controller;
    private CardConversion conversion;

    private Vector2 mousePosition = new Vector2(0, 0);

    private CardHand cardHand = new CardHand();

    private UIElement placeButton;

    private UIElement textInput;

    public Plump(GameState state, GameController controller, ViewController mainView) {
        this.state = state;
        this.controller = controller;
        conversion = new CardConversion();
    }

    @Override
    public void createView() {
        textInput = UIElementFactory.createTextInputField(
            ViewInformation.font,
             "0",
              2,
               new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16)
            );
        textInput.setPosition(50, 50);
        textInput.setScale(3, 3);


        placeButton = UIElementFactory.createGreenButton(
                ViewInformation.font,
                "Place",
                new ButtonAction() {
                @Override
                public void action() {
                    // Send the input to the controller if a card is selected
                    if (cardHand.getSelectIndex() > -1) {
                        controller.handleAction((state.getCurrentPlayer() + 1) % state.getPlayers().size(), "",
                                conversion.intToRank(cardHand.getSelectedCard()),
                                conversion.intToSuit(cardHand.getSelectedCard()));
                        }
                    }
                }
            
            );
                
        // Add a "on click" function to the guess button

        placeButton.setPosition(400, 50);
        placeButton.setScale(5, 3);

        cardHand.addCard(conversion.cardToInt(Suit.CLUBS.name(), Rank.ACE.name()),
                new Vector2(ViewInformation.screenSize.x / 2, ViewInformation.screenSize.y / 2));
    }

    @Override
    public void update() {
        cardHand.update(mousePosition);

    }

    @Override
    public void updateState() {
    }

    @Override
    public void mouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;
        textInput.mouseUpdate(mousePosition);
        placeButton.mouseUpdate(mousePosition);
        if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT))
            cardHand.selectCard();
    }

    @Override
    public void draw(SpriteBatch batch) {
        cardHand.draw(batch);
        placeButton.draw(batch);
        textInput.draw(batch);
    }

}
