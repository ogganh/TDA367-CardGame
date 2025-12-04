package TDA367.CardGame.model.card_logic;

public interface PlayableStackInterface {

    public Card remove_card();

    public void add_card(Card new_card);

    public int size();    
}