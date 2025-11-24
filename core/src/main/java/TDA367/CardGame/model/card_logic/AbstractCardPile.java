package TDA367.CardGame.model.card_logic;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractCardPile {

    ArrayList<Card> cards;

    // Tar bort och returnerar det översta kortet i kortleken, eller null om
    // kortleken är tom
    public Card remove_card() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    // Lägger till ett kort i kortleken
    public void add_card(Card new_card) {
        cards.add(new_card);
    }

    // Returnerar antalet kort i kortleken
    public int size() {
        return cards.size();
    }

    // Blanda befintliga kort i kortleken
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