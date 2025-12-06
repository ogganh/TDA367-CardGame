package TDA367.CardGame.model.card_logic;
import java.util.ArrayList;

/**
 * Unlike CardDeck, CardPile is not bound to a fixed size or content.
 * This class can be used to represent a player's hand or other piles in the game.
 */

public class CardPile implements PlayableStackInterface {

    ArrayList<Card> cardsInHand;

    public CardPile() {
        cardsInHand = new ArrayList<Card>();
    }

    @Override
    public Card removeCard() {
        if (cardsInHand.size() == 0) {
            return null;
        }
        return cardsInHand.remove(cardsInHand.size() - 1);
    }

    @Override
    public void addCard(Card newCard) {
        cardsInHand.add(newCard);
    }

    @Override
    public int size() {
        return cardsInHand.size();
    }
}