package TDA367.CardGame;

import TDA367.CardGame.gameLogic.strategies.GameStrategy;
import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.Card;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.card_logic.Rank;
import TDA367.CardGame.model.player.GoFishUserPlayer;

import java.util.List;
import java.util.ArrayList;

public class GoFishRules implements GameStrategy {

    private final List<GoFishUserPlayer> players;
    private final CardDeck deck;
    private final TurnManager turnManager;

    public GoFishRules(List<GoFishUserPlayer> players, CardDeck deck) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("GoFishGame requires 2 players");
        }
        this.players = new ArrayList<>(players);
        this.deck = deck;
        this.turnManager = new TurnManager(players.size());
    }


    @Override
    public void setup(GameState state) {
        deck.shuffle_deck();
        int cards_per_player = 7;

        // delar ut 7 kort per spelare så länge det finns kort i sjön
        for (int i = 0; i < cards_per_player; i++) {
            for (GoFishUserPlayer player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.remove_card();
                    player.add_card(drawn);
                }
            }
        }

        // räknar om det finns 4 av samma
        for (GoFishUserPlayer player : players) {
            player.collect_books();
        }
    }

    @Override
    public void handleTurn(GameState state, PlayerAction action) {
        //  tolkar playerIndex i PlayerAction som "vilken motståndare jag frågar"
        int opponentIndex = action.getPlayerIndex();
        // rank är en String i PlayerAction
        Rank requestedRank = Rank.valueOf(action.getRank());
        handleTurn(opponentIndex, requestedRank);
    }

    @Override
    public boolean isGameOver(GameState state) { //spelet är över om korten i sjön är slut
        return deck.isEmpty();
    }


    public void endTurn() {
        turnManager.next(); //metod i turnManger som byter aktiv spelare
    }

    public void endGame() {
    }


    public void handleTurn(int opponentIndex, Rank requestedRank) {
        int currentIndex = turnManager.GetCurrentIndex();
        GoFishUserPlayer current = players.get(currentIndex);


        if (opponentIndex == currentIndex) {
            throw new IllegalArgumentException("Player can not ask it self");
        }

        // hämta motståndaren
        GoFishUserPlayer opponent = players.get(opponentIndex);

        if (opponent.has_rank(String.valueOf(requestedRank))) {
            List<Card> taken = opponent.give_cards(String.valueOf(requestedRank));
            for (Card c : taken) {
                current.add_card(c);
            }
            current.collect_books();

        } else {
            if (!deck.isEmpty()) {
                Card drawn = deck.remove_card();
                current.add_card(drawn);
                current.collect_books();
            }
            endTurn(); // turnManager.next()
        }
    }

    //den med flest 4par vinner
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
                tie = true; // minst två har samma score
            }
        }

        if (tie) {
            return null; // oavgjort
        } else {
            return winner;
        }
    }
}
