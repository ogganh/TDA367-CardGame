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

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class GoFishRules implements GameStrategy {

    private final List<GoFishUserPlayer> players;
    private final CardDeck deck;
    private final TurnManager turnManager;
    private GameState state;
    private boolean endTurn = false;

    public GoFishRules(List<? extends UserPlayer> players, CardDeck deck) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("GoFishGame requires 2 players"); // Minst 2 spelare
        }

        this.players = new ArrayList<>();

        for (UserPlayer p : players) {
            if (!(p instanceof GoFishUserPlayer)) {
                throw new IllegalArgumentException("All players must be GoFishUserPlayer"); // Alla spelare måste vara
                                                                                            // GoFishUserPlayer
            }
            this.players.add((GoFishUserPlayer) p);
        }

        this.deck = deck;
        this.turnManager = new TurnManager(this.players.size());
    }

    @Override
    public void setup(GameState state) {
        this.state = state;
        state.setTurnManager(this.turnManager);
        deck.shuffleDeck();
        int cardsPerPlayer = 7; // Standard: 7 kort per spelare

        for (int i = 0; i < cardsPerPlayer; i++) { // Initial utdelning
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.removeCard();
                    player.addCard(drawn);
                }
            }
        }

        for (GoFishUserPlayer player : players) { // Kolla efter initiala books
            player.collectBooks();
        }
    }

    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        if (action.getActionType() == "SORT") {
            GoFishUserPlayer current = players.get(turnManager.getCurrentIndex());
            current.sortHand();
            return;
        }
        int opponentIndex = action.getPlayerIndex(); // Index för motståndaren
        Rank requestedRank = Rank.valueOf(action.getRank()); // Begärt rank
        handleTurnImpl(opponentIndex, requestedRank); // Hantera turen
    }

    @Override
    public boolean isGameOver(GameState state) {

        if (!deck.isEmpty()) { // Om sjön inte är tom, är spelet inte över
            return false;
        }

        for (GoFishUserPlayer p : players) {
            if (p.getHand().isEmpty()) { // Kontrollerar om minst spelare har tom hand
                return true;
            }
        }
        return false; // Spelet fortsätter annars
    }

    public void endTurn() {
        if (!endTurn) {
            return; // Turen fortsätter
        }
        state.openMiddleScreen();
        turnManager.next();
    }

    public void endGame() {
        // Eventuell logik för att avsluta spelet
    }

    private boolean refillAndEndTurnIfEmpty(GoFishUserPlayer player) {
        if (player.getHand().isEmpty()) { // Om spelarens hand är tom
            if (deck.isEmpty()) {
                return false; // Inget att dra, turen fortsätter
            }

            Card drawn = deck.removeCard(); // Dra ett kort från sjön
            player.addCard(drawn); // Lägg till kortet i spelarens hand
            player.collectBooks(); // Kolla efter böcker

            // Om man drar på grund av tom hand, går turen vidare (för att undvika låsning i
            // vyn)
            endTurn();
            return true;
        }
        return false;
    }

    public void handleTurnImpl(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.getCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);

        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self"); // Spelare kan inte fråga sig själv
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
                current.addCard(c); // Ge kort till den aktiva spelaren om motståndaren har dem
            }
            current.collectBooks();

            if (refillAndEndTurnIfEmpty(opponent)) { // Kolla motståndarens hand, ge kort om han är tom
                return;
            }

            if (refillAndEndTurnIfEmpty(current)) { // Kolla den aktiva spelarens hand, ge kort om han är tom
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

                if (refillAndEndTurnIfEmpty(current)) { // Kolla den aktiva spelarens hand, ge kort om han är tom
                    return;
                }
            }
            endTurn = true; // Turen går alltid vidare efter Go Fish.
        }
    }

    // Vinstregler: Den med flest 4-tal (böcker) vinner, oavsett när spelet slutade.
    public GoFishUserPlayer getWinner() {
        GoFishUserPlayer winner = null;
        int bestScore = -1;
        boolean tie = false;

        for (GoFishUserPlayer p : players) {
            int score = p.getPoints(); // Antal böcker

            if (score > bestScore) {
                bestScore = score;
                winner = p;
                tie = false;
            } else if (score == bestScore) {
                tie = true;
            }
        }

        if (tie) {
            return null; // oavgjort
        } else {
            return winner;
        }
    }

    public int getCurrentPlayerIndex() {
        return turnManager.getCurrentIndex();
    }
}
