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

        for (int i = 0; i < cards_per_player; i++) {
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.remove_card();
                    player.add_card(drawn);
                }
            }
        }

        for (GoFishUserPlayer player : players) {
            player.collect_books();
        }
    }

    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        int opponentIndex = action.getPlayerIndex();
        Rank requestedRank = Rank.valueOf(action.getRank());
        handleTurn(opponentIndex, requestedRank);
    }

    @Override
    public boolean isGameOver(GameState state) {
        // REGLER: Spelet är över om sjön är tom OCH MINST EN spelare har tom hand.
        if (!deck.isEmpty()) {
            return false;
        }

        for (GoFishUserPlayer p : players) {
            if (p.get_hand().isEmpty()) { // Kontrollerar om MINST EN spelare har tom hand
                return true;
            }
        }
        return false;
    }

    public void endTurn() {
        turnManager.next();
        state.SetCurrentPlayer((state.GetCurrentPlayer()+1)%state.getPlayers().size());
    }

    public void endGame() {
        // Eventuell logik för att avsluta spelet
    }

    /**
     * HANTERING AV TOM HAND: Om handen är tom och sjön inte är tom, drar spelaren ett kort OCH TUREN GÅR VIDARE.
     * @param player Spelaren att kontrollera.
     * @return true om påfyllning skedde OCH turen byttes.
     */
    private boolean refillAndEndTurnIfEmpty(GoFishUserPlayer player) {
        if (player.get_hand().isEmpty()) {
            if (deck.isEmpty()) {
                return false; // Kan inte dra, spelet kan vara över
            }

            Card drawn = deck.remove_card();
            player.add_card(drawn);
            player.collect_books();

            // DIN REGL: Om man drar på grund av tom hand, går turen vidare (för att undvika låsning i vyn)
            endTurn();
            return true;
        }
        return false;
    }


    public void handleTurn(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.GetCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);

        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self");
        }

        GoFishUserPlayer opponent = players.get(opponentIndex);

        if (opponent.has_rank(String.valueOf(requestedRank))) {
            // --- LYCKAD FRÅGA: Fick kort (Standard: behåll turen) ---
            List<Card> taken = opponent.give_cards(String.valueOf(requestedRank));
            for (Card c : taken) {
                current.add_card(c);
            }
            current.collect_books();

            // 1. Kolla motståndaren. Om motståndaren fylls på, byts turen.
            if (refillAndEndTurnIfEmpty(opponent)) {
                return;
            }

            // 2. Kolla aktiva spelaren. Om den fylls på, byts turen.
            if (refillAndEndTurnIfEmpty(current)) {
                return;
            }

            // Om turen inte bytts ovan: Spelaren behåller turen (standard Go Fish)

        } else {
            // --- MISSLYCKAD FRÅGA: Go Fish ---
            if (!deck.isEmpty()) {
                Card drawn = deck.remove_card();
                current.add_card(drawn);
                current.collect_books();

                if (refillAndEndTurnIfEmpty(current)){
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
