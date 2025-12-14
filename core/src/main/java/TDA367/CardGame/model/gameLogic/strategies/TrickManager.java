package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.model.card_logic.Card;

import java.util.ArrayList;
import java.util.List;

public class TrickManager {

    private final CardConversion cardConversion = new CardConversion();

    private List<Card> currentTrick;
    private String leadSuit;
    private int fisrtPlayerInTrickIndex;
    private final int playersCount;

    public TrickManager(int playersCount) {
        this.playersCount = playersCount;
        this.currentTrick = new ArrayList<>();
        this.leadSuit = null;
        this.fisrtPlayerInTrickIndex = -1;
    }

    public void startNewTrick(int firstPlayerIndex) {
        this.currentTrick.clear();
        this.leadSuit = null;
        this.fisrtPlayerInTrickIndex = firstPlayerIndex;
    }

    public void playCard(Card card) {
        if (currentTrick.isEmpty()) {
            leadSuit = card.getSuit();
        }
        currentTrick.add(card);
    }

    public boolean isTrickComplete() {
        return currentTrick.size() == playersCount;
    }

    public int getTrickWinner() {
        if (!isTrickComplete()) {
            throw new IllegalStateException("Trick is not complete yet.");
        }
        if (currentTrick.isEmpty()) {
            throw new IllegalStateException("No cards in trick.");
        }
        if (leadSuit == null) {
            throw new IllegalStateException("leadSuit not set.");
        }


        Card winningCard = currentTrick.get(0);
        int winnerTrickIndex = 0;


        for (int i = 1; i < currentTrick.size(); i++) {
            Card currentCard = currentTrick.get(i);

            if (compareCards(currentCard, winningCard)) {
                winningCard = currentCard;
                winnerTrickIndex = i;
            }
        }


        return (fisrtPlayerInTrickIndex + winnerTrickIndex) % playersCount;
    }

    private boolean compareCards(Card newCard, Card winningCard) {

        if (!newCard.getSuit().equals(leadSuit)) {
            return false;
        }

        int newCardValue = cardConversion.cardToInt(newCard.getSuit(), newCard.getRank());
        int winningCardValue = cardConversion.cardToInt(winningCard.getSuit(), winningCard.getRank());

        return newCardValue > winningCardValue;
    }

    public String getLeadSuit() {
        return leadSuit;
    }

    public int getFirstPlayerInTrickIndex() {
        return fisrtPlayerInTrickIndex;
    }

    public List<Card> getCurrentTrick() {
        return new ArrayList<>(currentTrick);
    }
}

