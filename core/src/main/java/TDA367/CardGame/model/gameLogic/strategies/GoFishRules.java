package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.LastAction;
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
    private boolean endTurn = false;

    /**
     * Creates a GoFishRules instance with a list of players and a deck.
     * Requires at least 2 players and that all players are GoFishUserPlayer.
     */

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

    /**
     * Sets up the initial game state: shuffles the deck, deals 7 cards per player,
     * and lets each player collect any initial books.
     */
    @Override
    public void setup(GameState state) {
        this.state = state;
        state.setTurnManager(this.turnManager);
        deck.shuffleDeck();
        int cardsPerPlayer = 7; // Standard: 7 kort per spelare

        for (int i = 0; i < cardsPerPlayer; i++) {
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.removeCard();
                    player.addCard(drawn);
                }
            }
        }

        for (GoFishUserPlayer player : players) {
            player.collectBooks();
        }
    }

    /**
     * Handles a player's action for the current turn.
     * If the action is SORT, the current player sorts their hand.
     * Otherwise the current player asks an opponent for a specific rank.
     */
    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        if (action.getActionType() == "SORT") {
            GoFishUserPlayer current = players.get(turnManager.getCurrentIndex());
            current.sortHand();
            return;
        }
        int opponentIndex = action.getPlayerIndex();
        Rank requestedRank = Rank.valueOf(action.getRank());
        handleTurnImpl(opponentIndex, requestedRank);
    }

    /**
     * Checks if the game is over.
     * The game ends when the deck is empty and at least one player has an empty hand.
     */
    @Override
    public boolean isGameOver(GameState state) {

        if (!deck.isEmpty()) {
            return false;
        }

        for (GoFishUserPlayer p : players) {
            if (p.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ends the current player's turn if endTurn is true.
     * Opens the middle screen and advances to the next player.
     */

    public void endTurn() {
        if (!endTurn) {
            return;
        }
        state.openMiddleScreen();
        turnManager.next();
    }

    /**
     * If the given player has an empty hand, draws one card from the deck (if possible),
     * collects books, and ends the turn.
     * Returns true if a refill happened and the turn was ended.
     */
    private boolean refillAndEndTurnIfEmpty(GoFishUserPlayer player) {
        if (player.getHand().isEmpty()) {
            if (deck.isEmpty()) {
                return false;
            }

            Card drawn = deck.removeCard();
            player.addCard(drawn);
            player.collectBooks();

            endTurn();
            return true;
        }
        return false;
    }

    /**
     * Core Go Fish logic: the current player asks opponentIndex for requestedRank.
     * If the opponent has the rank, cards are transferred and the current player continues.
     * Otherwise the current player draws from the deck and the turn will end.
     */

    public void handleTurnImpl(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.getCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);

        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self");
        }

        GoFishUserPlayer opponent = players.get(opponentIndex);

        if (opponent.hasRank(String.valueOf(requestedRank))) {
            endTurn = false;
            LastAction a = new LastAction();
            a.source = LastAction.SourceType.OPPONENT;
            a.sourcePlayerIndex = opponentIndex;
            a.targetPlayerIndex = currentIndex;
            state.setLastAction(a);

            List<Card> taken = opponent.giveCards(String.valueOf(requestedRank));
            for (Card c : taken) {
                current.addCard(c);
            }
            current.collectBooks();

            if (refillAndEndTurnIfEmpty(opponent)) {
                return;
            }

            if (refillAndEndTurnIfEmpty(current)) {
                return;
            }

        } else {
            LastAction a = new LastAction();
            a.source = LastAction.SourceType.POND;
            a.sourcePlayerIndex = opponentIndex;
            a.targetPlayerIndex = currentIndex;
            state.setLastAction(a);
            if (!deck.isEmpty()) {
                Card drawn = deck.removeCard();
                current.addCard(drawn);
                current.collectBooks();

                if (refillAndEndTurnIfEmpty(current)) {
                    return;
                }
            }
            endTurn = true;
        }
    }

    /**
     * Finds the winner by highest points.
     * Returns null if two or more players share the highest score (tie).
     */


    public GoFishUserPlayer getWinner() {
        GoFishUserPlayer winner = null;
        int bestScore = -1;
        boolean tie = false;

        for (GoFishUserPlayer p : players) {
            int score = p.getPoints();

            if (score > bestScore) {
                bestScore = score;
                winner = p;
                tie = false;
            } else if (score == bestScore) {
                tie = true;
            }
        }

        if (tie) {
            return null;
        } else {
            return winner;
        }
    }

    public int getCurrentPlayerIndex() {
        return turnManager.getCurrentIndex();
    }
}
