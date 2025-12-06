package TDA367.CardGame.model.player;
import TDA367.CardGame.model.card_logic.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction of a player for a generic card game. Keeps track of the player name,
 * the cards in hand, and methods to add and remove them.
 */

public abstract class UserPlayer implements IPlayer{
    private final String name;
    protected final List<Card> hand;

    public UserPlayer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    @Override
    public String getName(){
        return this.name;
    }

    public void addCard(Card card){
        if (card != null){
            this.hand.add(card);
        }
    }

    public void removeCard(Card card){
        if (card != null){
            this.hand.remove(card);
        }
    }
    public List<Card> getHand(){
        return new ArrayList<>(this.hand);
    }

}