package TDA367.CardGame.model.card_logic;
import java.util.ArrayList;

/**
 * I kontrast till CardDedk är inte CardPile inte bunden till en fast storlek eller innehåll.
 * Denna klass kan användas för att representera en spelares hand eller andra korthög i spelet. 
 */

public class CardPile implements PlayableStackInterface {

    ArrayList<Card> cardsInHand;

    public CardPile() {
        cardsInHand = new ArrayList<Card>();
    }

    @Override
    public Card remove_card() {
        if (cardsInHand.size() == 0) {
            return null;
        }
        return cardsInHand.remove(cardsInHand.size() - 1);
    }

    @Override
    public void add_card(Card new_card) {
        cardsInHand.add(new_card);
    }

    @Override
    public int size() {
        return cardsInHand.size();
    }
}