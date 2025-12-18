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

    public void incPoints() {
        this.points++;
    }

    public int getPoints() {
        return this.points;
    }

    public void addCard(Card card) {
        super.addCard(card);
    }

    public boolean hasRank(String rank) {
        for (Card card : this.hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public List<Card> giveCards(String rank) {
        List<Card> cardsSameRank = new ArrayList<>();
        List<Card> removeFromHand = new ArrayList<>();

        for (Card card : this.hand) {
            if (card.getRank().equals(rank)) {
                cardsSameRank.add(card);
                removeFromHand.add(card);
            }
        }
        for (Card card : removeFromHand) {
            this.hand.remove(card);
        }
        return cardsSameRank;
    }

    public void collectBooks() {
        List<String> ranksChecked = new ArrayList<>();
        for (Card card : new ArrayList<>(this.hand)) { // iterate over a copy
            String rank = card.getRank();
            if (!ranksChecked.contains(rank)) {
                ranksChecked.add(rank);
                long count = this.hand.stream().filter(c -> c.getRank().equals(rank)).count();
                if (count == 4) {
                    // Remove all cards of this rank safely
                    this.hand.removeIf(c -> c.getRank().equals(rank));
                    this.incPoints();

                }
            }
        }
    }

    public List<Card> getHand() {
        return super.getHand();
    }
}
