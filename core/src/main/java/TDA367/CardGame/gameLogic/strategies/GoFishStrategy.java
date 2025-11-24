package TDA367.CardGame.gameLogic.strategies;

import java.util.ArrayList;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.card_logic.Card;
import TDA367.CardGame.model.player.GoFishUserPlayer;

import java.util.List;

public class GoFishStrategy implements GameStrategy {

    private final List<GoFishUserPlayer> players;
    private final CardDeck deck;
    private int current_player_index = 0;

    public GoFishStrategy(List<GoFishUserPlayer> players, CardDeck deck) {
        if (players.size() != 2) {
            throw new IllegalArgumentException("GofishGame require 2 players");
        }
        this.players = new ArrayList<>(players);
        this.deck = deck;
    }

    public void setup(GameState state) {
        deck.shuffle_deck();
        int cards_per_player = 7;

        for (int i = 0; i < cards_per_player; i++) {
            for (GoFishUserPlayer player : players) {
                if (deck.size() > 0) {
                    Card drawn = deck.remove_card();
                    player.add_card(drawn);
                }
            }
        }

        for (GoFishUserPlayer player : players) {
            player.collect_books();
        }

        current_player_index = 0;
    }

    public void handleTurn(GameState state, PlayerAction action) {
        GoFishUserPlayer current = players.get(current_player_index);
        GoFishUserPlayer opponent = players.get(1 - current_player_index);

        String requestedRank = action.getRank();

        // Motståndaren har kort av denna rank
        if (opponent.has_rank(requestedRank)) {
            List<Card> taken = opponent.give_cards(requestedRank);
            for (Card c : taken) {
                current.add_card(c);
            }
            current.collect_books();
        } else {

            // Finns i sjön
            if (!deck.isEmpty()) {
                Card drawn = deck.remove_card();
                current.add_card(drawn);

                current.collect_books();
            }

            current_player_index = 1 - current_player_index;
        }
    }

    public boolean isGameOver(GameState state) {
        return deck.size() == 0;
    }
}