package TDA367.CardGame.model.card_logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract class for a deck or pile of cards.
 */

public abstract class AbstractCardPile {

    ArrayList<Card> cards;

    // Removes and returns the top card of the pile, or null if
    // the pile is empty
    public Card removeCard() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    // Adds a card to the pile
    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    // Returns the number of cards in the pile
    public int size() {
        return cards.size();
    }

    // Shuffle existing cards in the pile
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card peek(){
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(cards.size() - 1);
    }

}