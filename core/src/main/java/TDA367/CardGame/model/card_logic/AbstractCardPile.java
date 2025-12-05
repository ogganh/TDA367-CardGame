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
    public Card remove_card() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    // Adds a card to the pile
    public void add_card(Card new_card) {
        cards.add(new_card);
    }

    // Returns the number of cards in the pile
    public int size() {
        return cards.size();
    }

    // Shuffle existing cards in the pile
    public void shuffle_deck() {
        Collections.shuffle(cards);
    }

    public Card peak(){
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(cards.size() - 1);
    }

}