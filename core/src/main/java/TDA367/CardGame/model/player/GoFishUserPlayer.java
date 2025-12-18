package TDA367.CardGame.model.player;

import TDA367.CardGame.model.card_logic.Card;
import TDA367.CardGame.model.card_logic.Rank;

import java.util.List;

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

    public void sortHand() {
        this.hand.sort((card1, card2) -> {
            int rankComparison = Integer.compare(
                    rankToValue(card1.getRank()),
                    rankToValue(card2.getRank()));
            return rankComparison;
        });
    }

    public static int rankToValue(String rank) {
        switch (rank) {
            case "TWO":
                return 2;
            case "THREE":
                return 3;
            case "FOUR":
                return 4;
            case "FIVE":
                return 5;
            case "SIX":
                return 6;
            case "SEVEN":
                return 7;
            case "EIGHT":
                return 8;
            case "NINE":
                return 9;
            case "TEN":
                return 10;
            case "JACK":
                return 11;
            case "QUEEN":
                return 12;
            case "KING":
                return 13;
            case "ACE":
                return 14;
            default:
                throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }
}