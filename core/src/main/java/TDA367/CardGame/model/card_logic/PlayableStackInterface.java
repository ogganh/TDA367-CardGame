package TDA367.CardGame.model.card_logic;

public interface PlayableStackInterface {

    public Card removeCard();

    public void addCard(Card newCard);

    public int size();    
}