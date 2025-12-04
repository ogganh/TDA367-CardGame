package TDA367.CardGame.model.card_logic;

import java.util.ArrayList;

    /** Beskriver en fixerad kortlek för att begränsa så att samma kort inte förekommer mer än en gång.
    * Tanken är att denna klass fungerar som en kortpool där kort kan dras ifrån och läggas till.
    * Detta förindrar att samma kort förekommer mer än en gång i spelet.
    */


public class CardDeck extends AbstractCardPile implements PlayableStackInterface {

    // Begränsar maxstorleken på kortleken
    private final int DECK_SIZE = 52;

    // Konstruktor som skapar och blandar en ny kortlek
    public CardDeck() {
        cards = new ArrayList<Card>(DECK_SIZE); // Skapa en tom kortlek
        initialize_deck(); // Fyll kortleken med 52 kort
        shuffle_deck(); // Blanda kortleken
    }

    // Initialisera kortleken med 52 kort genom att kombinera alla färger och
    // valörer
    private void initialize_deck() {
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