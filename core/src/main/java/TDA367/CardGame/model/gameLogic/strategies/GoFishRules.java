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
            throw new IllegalArgumentException("GoFishGame requires 2 players"); // Minst 2 spelare
        }

        this.players = new ArrayList<>();

        for (UserPlayer p : players) {
            if (!(p instanceof GoFishUserPlayer)) {
                throw new IllegalArgumentException("All players must be GoFishUserPlayer"); // Alla spelare måste vara GoFishUserPlayer
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
        int cards_per_player = 7; // Standard: 7 kort per spelare

        for (int i = 0; i < cards_per_player; i++) { // Initial utdelning
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.remove_card();
                    player.add_card(drawn);
                }
            }
        }

        for (GoFishUserPlayer player : players) { // Kolla efter initiala books
            player.collect_books();
        }
    }

    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        int opponentIndex = action.getPlayerIndex(); // Index för motståndaren
        Rank requestedRank = Rank.valueOf(action.getRank()); // Begärt rank
        handleTurn(opponentIndex, requestedRank); // Hantera turen
    }

    @Override
    public boolean isGameOver(GameState state) {

        if (!deck.isEmpty()) { // Om sjön inte är tom, är spelet inte över
            return false;
        }

        for (GoFishUserPlayer p : players) {
            if (p.get_hand().isEmpty()) { // Kontrollerar om minst spelare har tom hand
                return true;
            }
        }
        return false; // Spelet fortsätter annars
    }

    public void endTurn() {
        state.openMiddleScreen();
        turnManager.next();
        state.SetCurrentPlayer((state.GetCurrentPlayer()+1)%state.getPlayers().size());
    }

    public void endGame() {
        // Eventuell logik för att avsluta spelet
    }

    private boolean refillAndEndTurnIfEmpty(GoFishUserPlayer player) {
        if (player.get_hand().isEmpty()) { // Om spelarens hand är tom
            if (deck.isEmpty()) {
                return false; // Inget att dra, turen fortsätter
            }

            Card drawn = deck.remove_card(); // Dra ett kort från sjön
            player.add_card(drawn); // Lägg till kortet i spelarens hand
            player.collect_books(); // Kolla efter böcker

            // Om man drar på grund av tom hand, går turen vidare (för att undvika låsning i vyn)
            endTurn();
            return true;
        }
        return false;
    }


    public void handleTurn(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.GetCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);

        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self"); // Spelare kan inte fråga sig själv
        }

        GoFishUserPlayer opponent = players.get(opponentIndex);

        if (opponent.has_rank(String.valueOf(requestedRank))) {

            List<Card> taken = opponent.give_cards(String.valueOf(requestedRank));
            for (Card c : taken) {
                current.add_card(c); // Ge kort till den aktiva spelaren om motståndaren har dem
            }
            current.collect_books();


            if (refillAndEndTurnIfEmpty(opponent)) { // Kolla motståndarens hand, ge kort om han är tom
                return;
            }


            if (refillAndEndTurnIfEmpty(current)) { // Kolla den aktiva spelarens hand, ge kort om han är tom
                return;
            }



        } else {

            if (!deck.isEmpty()) {
                Card drawn = deck.remove_card();
                current.add_card(drawn);
                current.collect_books();

                if (refillAndEndTurnIfEmpty(current)){ // Kolla den aktiva spelarens hand, ge kort om han är tom
                    return;
                }
            }
            endTurn(); // Turen går alltid vidare efter Go Fish.
        }
    }

    // Vinstregler: Den med flest 4-tal (böcker) vinner, oavsett när spelet slutade.
    public GoFishUserPlayer get_winner() {
        GoFishUserPlayer winner = null;
        int bestScore = -1;
        boolean tie = false;

        for (GoFishUserPlayer p : players) {
            int score = p.get_points(); // Antal böcker

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
        return turnManager.GetCurrentIndex();
    }
}
