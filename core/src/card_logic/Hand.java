public class Hand implements PlayableStackInterface {

    ArrayList<Card> cardsInHand;

    public Hand() {
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
