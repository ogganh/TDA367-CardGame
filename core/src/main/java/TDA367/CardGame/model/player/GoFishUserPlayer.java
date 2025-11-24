package TDA367.CardGame.model.player;
import TDA367.CardGame.model.card_logic.Card;
import java.util.List;
import java.util.ArrayList;


public class GoFishUserPlayer extends UserPlayer{

    private int points; //Tracking how many stacks of 4 of a kind player have put down

    public GoFishUserPlayer(String name){
        super(name);
        this.points = 0;
    }
    public void inc_points(){
        this.points++;
    }
    public int get_points(){
        return this.points;
    }
    public void add_card(Card card){
        super.add_card(card);
    }
    public boolean has_rank(String rank){
        for (Card card : this.hand){
            if (card.getRank().equals(rank)){
                return true;
            }
        }
        return false;
    }
    public List<Card> give_cards(String rank){
        List<Card> cards_same_rank = new ArrayList<>();
        List<Card> remove_from_hand = new ArrayList<>();

        for(Card card : this.hand){
            if (card.getRank().equals(rank)){
                cards_same_rank.add(card);
                remove_from_hand.add(card);
            }
        }
        for (Card card : remove_from_hand){
            this.hand.remove(card);
        }
        return cards_same_rank;
    }
    public void collect_books(){
        List<String> ranks_checked = new ArrayList<>();
        for (Card card : this.hand){
            String rank = card.getRank();
            if (!ranks_checked.contains(rank)){
                ranks_checked.add(rank);
                int count = 0;
                for (Card c : this.hand){
                    if (c.getRank().equals(rank)){
                        count++;
                    }
                }
                if (count == 4){
                    //Remove all cards of this rank from hand
                    List<Card> remove_from_hand = new ArrayList<>();
                    for (Card c : this.hand){
                        if (c.getRank().equals(rank)){
                            remove_from_hand.add(c);
                        }
                    }
                    for (Card c : remove_from_hand){
                        this.hand.remove(c);
                    }
                    //Increment points
                    this.inc_points();
                }
            }
        }
    }

    public List<Card> get_hand(){
        return super.get_hand();
    }
}