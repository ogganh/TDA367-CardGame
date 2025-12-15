package TDA367.CardGame.model.gameLogic.strategies;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.gameLogic.PlumpPhase;
import TDA367.CardGame.model.gameLogic.strategies.GameStrategy;
import TDA367.CardGame.model.gameLogic.strategies.TurnManager;
import TDA367.CardGame.model.player.PlumpUserPlayer;
import TDA367.CardGame.model.player.UserPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlumpRules implements GameStrategy {

    private final List<PlumpUserPlayer> players;
    private final TurnManager turnManager;
    private final CardDeck deck;

    private GameState state; // inte final
    private PlumpPhase phase = PlumpPhase.GUESSING;

    private int[] guesses;
    private int cardsPerPlayer = 10;

    public PlumpRules(List<? extends UserPlayer> players, CardDeck deck) {
        if (players.size() < 2 || players.size() > 4) {
            throw new IllegalArgumentException("Plump requires 2-4 players");
        }

        this.players = new ArrayList<>();
        for (UserPlayer p : players) {
            if (!(p instanceof PlumpUserPlayer)) {
                throw new IllegalArgumentException("All players must be PlumpUserPlayer");
            }
            this.players.add((PlumpUserPlayer) p);
        }

        this.deck = deck;
        this.turnManager = new TurnManager(this.players.size());
        this.guesses = new int[this.players.size()];
    }

    @Override
    public void setup(GameState state) {
        this.state = state;
        cardsPerPlayer = 10;
        phase = PlumpPhase.GUESSING;
        resetGuesses(); //måste finnas
        startRound();
    }
    private void startRound() {
        deck.shuffleDeck();
        clearHands(); //måste finnas
        resetGuesses();
        deal(cardsPerPlayer);
        state.setCurrentPlayer(turnManager.getCurrentIndex());
    }

    private void resetGuesses() {
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = -1;
        }
    }


    @Override
    public void handleTurn(GameState state, PlayerAction action) {

    }

    @Override
    public int getCurrentPlayerIndex() {
        return 0;
    }

    @Override
    public boolean isGameOver(GameState state) {
        return false;
    }
}
