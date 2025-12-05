package TDA367.CardGame.model.player;

import TDA367.CardGame.model.card_logic.Card;
import java.util.List;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * A specific GoFish player that has points and cards (extends UserPlayer).
 * It also handles logic for what should happen on various moves in GoFish.
 */

public class GoFishUserPlayer extends UserPlayer {

    private int points; // Tracking how many stacks of 4 of a kind player have put down

    public GoFishUserPlayer(String name) {
        super(name);
        this.points = 0;
    }

    public void inc_points() {
        this.points++;
    }

    public int get_points() {
        return this.points;
    }

    public void add_card(Card card) {
        super.add_card(card);
    }

    public boolean has_rank(String rank) {
        for (Card card : this.hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public List<Card> give_cards(String rank) {
        List<Card> cards_same_rank = new ArrayList<>();
        List<Card> remove_from_hand = new ArrayList<>();

        for (Card card : this.hand) {
            if (card.getRank().equals(rank)) {
                cards_same_rank.add(card);
                remove_from_hand.add(card);
            }
        }
        for (Card card : remove_from_hand) {
            this.hand.remove(card);
        }
        return cards_same_rank;
    }

    public void collect_books() {
        List<String> ranksChecked = new ArrayList<>();
        for (Card card : new ArrayList<>(this.hand)) { // iterate over a copy
            String rank = card.getRank();
            if (!ranksChecked.contains(rank)) {
                ranksChecked.add(rank);
                long count = this.hand.stream().filter(c -> c.getRank().equals(rank)).count();
                if (count == 4) {
                    // Remove all cards of this rank safely
                    this.hand.removeIf(c -> c.getRank().equals(rank));
                    this.inc_points();
                    Gdx.app.log("GoFishUserPlayer", this.get_name() + " collected a book of " + rank + "s. Total points: " + this.get_points());
                }
            }
        }
    }

    public List<Card> get_hand() {
        return super.get_hand();
    }
}