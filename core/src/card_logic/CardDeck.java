import java.util.ArrayList;
import java.util.Collection;

import javax.smartcardio.Card;

public class CardDeck implements PlayableStack {

    static final int DECK_SIZE = 52;

    private ArrayList<Card> cards;

    // Konstruktor som skapar och blandar en ny kortlek
    public CardDeck() {
        cards = new ArrayList<card>(DECK_SIZE); // Skapa en tom kortlek
        initialize_deck(); // Fyll kortleken med 52 kort
        shuffle_deck(); // Blanda kortleken
    }

    // Initialisera kortleken med 52 kort genom att kombinera alla färger och
    // valörer
    private void initialize_deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new card(suit.name(), rank.name()));
            }
        }
    }

    // Blanda befintliga kort i kortleken
    public void shuffle_deck() {
        Collections.shuffle(cards);
    }

    @Override
    // Lägger till ett kort i kortleken
    public void add_card(card new_card) {
        cards.add(new_card);
    }

    @Override
    // Tar bort och returnerar det översta kortet i kortleken, eller null om
    // kortleken är tom
    public card remove_card() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.pop();
    }

    // Returnerar antalet kort i kortleken
    public int size() {
        return cards.size();
    }
/* 
 * Ha två decks på ett bord som du kan shuffla shuffla 
 * 
 * Compare metoder 
 * 
 * 
 */
}
