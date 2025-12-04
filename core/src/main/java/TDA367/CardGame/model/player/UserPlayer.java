package TDA367.CardGame.model.player;
import TDA367.CardGame.model.card_logic.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstraktion av en spelare till ett godtyckligt kortspel. Har koll på namn,
 * kort på hand
 * och metoder för att lägga till och ta bort dessa.
 */

public abstract class UserPlayer implements IPlayer{
    private final String name;
    protected final List<Card> hand;

    public UserPlayer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    @Override
    public String get_name(){
        return this.name;
    }

    public void add_card(Card card){
        if (card != null){
            this.hand.add(card);
        }
    }

    public void remove_card(Card card){
        if (card != null){
            this.hand.remove(card);
        }
    }
    public List<Card> get_hand(){
        return new ArrayList<>(this.hand);
    }

}