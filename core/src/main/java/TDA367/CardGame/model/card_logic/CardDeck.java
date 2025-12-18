package TDA367.CardGame.model.card_logic;

import java.util.ArrayList;

    /**
     * Represents a fixed deck to ensure the same card does not appear more than once.
     * This class acts as a card pool from which cards can be drawn and added.
     * It prevents duplicate cards from appearing in the game.
     */


public class CardDeck extends AbstractCardPile {

    // Limits the maximum deck size
    private final int DECK_SIZE = 52;

    // Constructor that creates and shuffles a new deck
    public CardDeck() {
        cards = new ArrayList<Card>(DECK_SIZE); // Create an empty deck
        initializeDeck(); // Fill the deck with 52 cards
        shuffleDeck(); // Shuffle the deck
    }

    // Initialize the deck with 52 cards by combining all suits and
    // ranks
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit.name(), rank.name()));
            }
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}