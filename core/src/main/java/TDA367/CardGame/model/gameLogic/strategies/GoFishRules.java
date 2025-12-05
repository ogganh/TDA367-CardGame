package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.Card;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.card_logic.Rank;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.player.UserPlayer;

import java.util.List;
import java.util.ArrayList;

public class GoFishRules implements GameStrategy {

    private final List<GoFishUserPlayer> players;
    private final CardDeck deck;
    private final TurnManager turnManager;
    private GameState state;

    public GoFishRules(List<? extends UserPlayer> players, CardDeck deck) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("GoFishGame requires 2 players");
        }

        this.players = new ArrayList<>();

        for (UserPlayer p : players) {
            if (!(p instanceof GoFishUserPlayer)) {
                throw new IllegalArgumentException("All players must be GoFishUserPlayer");
            }
            this.players.add((GoFishUserPlayer) p);
        }

        this.deck = deck;
        this.turnManager = new TurnManager(this.players.size());
    }

    @Override
    public void setup(GameState state) {
        this.state = state;
        deck.shuffle_deck();
        int cards_per_player = 7;

        // deal 7 cards per player as long as there are cards in the pool
        for (int i = 0; i < cards_per_player; i++) {
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.remove_card();
                    player.add_card(drawn);
                }
            }
        }

        // check again for any sets of four
        for (GoFishUserPlayer player : players) {
            player.collect_books();
        }
    }

    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        // interpret playerIndex in PlayerAction as "which opponent I'm asking"
        int opponentIndex = action.getPlayerIndex();
        // rank is a String in PlayerAction
        Rank requestedRank = Rank.valueOf(action.getRank());
        handleTurn(opponentIndex, requestedRank);
    }

    @Override
    public boolean isGameOver(GameState state) { // the game is over if the deck is empty and all players have no cards
        if (!deck.isEmpty()) {
            return false;
        }
        for (GoFishUserPlayer p : players) {
            if (!p.get_hand().isEmpty()) {
                System.out.println(p.get_name());
                return false;
            }
        }
        return true;
    }

    public void endTurn() {
        state.openMiddleScreen();
        turnManager.next(); // method in TurnManager that switches the active player
        state.SetCurrentPlayer((state.GetCurrentPlayer() + 1) % state.getPlayers().size());
    }

    public void endGame() {

    }

    // if the player has no cards in hand but the deck is not empty, give the player a card
    // from the deck.
    private void refillIfEmpty(GoFishUserPlayer player) {
        if (player.get_hand().isEmpty() && !deck.isEmpty()) {
            Card drawn = deck.remove_card();
            player.add_card(drawn);
            player.collect_books();
        }
    }

    public void handleTurn(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.GetCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);

        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self");
        }

        // retrieve the opponent
        GoFishUserPlayer opponent = players.get(opponentIndex);

        if (opponent.has_rank(String.valueOf(requestedRank))) {
            List<Card> taken = opponent.give_cards(String.valueOf(requestedRank));
            for (Card c : taken) {
                current.add_card(c);
            }
            current.collect_books(); // check if the active player collected a book (4 of a kind)
            refillIfEmpty(opponent); // if the opponent has no cards in hand
            refillIfEmpty(current); // if the active player has no cards in hand

        } else {
            if (!deck.isEmpty()) {
                Card drawn = deck.remove_card();
                current.add_card(drawn);
                current.collect_books();
            }
            endTurn(); // advance to the next player's turn
        }
    }

    // the player with the most books (4-of-a-kind) wins
    public GoFishUserPlayer get_winner() {
        GoFishUserPlayer winner = null;
        int bestScore = -1;
        boolean tie = false;

        for (GoFishUserPlayer p : players) {
            int score = p.get_points();

            if (score > bestScore) {
                bestScore = score;
                winner = p;
                tie = false;
            } else if (score == bestScore) {
                tie = true; // at least two players have the same score
            }
        }

        if (tie) {
            return null; // draw
        } else {
            return winner;
        }
    }

    public int getCurrentPlayerIndex() {
        return turnManager.GetCurrentIndex();
    }
}
