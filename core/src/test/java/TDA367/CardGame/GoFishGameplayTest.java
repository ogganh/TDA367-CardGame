package TDA367.CardGame;

import TDA367.CardGame.model.GameState;
import TDA367.CardGame.model.PlayerAction;
import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.gameLogic.GameContext;
import TDA367.CardGame.model.gameLogic.strategies.GoFishRules;
import TDA367.CardGame.model.player.GoFishUserPlayer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Integration tests that verify a full Go Fish game
 * can be played using ONLY the model layer.
 */

public class GoFishGameplayTest {

    /**
     * LibGDX must be mocked because the model uses Gdx.app.log(...)
     */
    @BeforeAll
    static void mockGdx() {
        // Mocking Gdx.app to avoid null pointers while logging.
        Gdx.app = mock(Application.class);
    }

    /**
     * Testing if a full game can be played
     * returns t/f
     */
    @Test
    void fullGame() {
        GameState state = new GameState();
        GoFishUserPlayer p1 = new GoFishUserPlayer("P1");
        GoFishUserPlayer p2 = new GoFishUserPlayer("P2");
        state.addPlayer(p1);
        state.addPlayer(p2);
        CardDeck deck = new CardDeck();
        state.addPile("deck", deck);
        GoFishRules rules = new GoFishRules(List.of(p1, p2), deck);
        GameContext context = new GameContext(state, rules);
        //Lines above is the setup

        // Each player should have 7 cards
        assertEquals(7, p1.getHand().size(), "P1 should have 7 cards from start");
        assertEquals(7, p2.getHand().size(), "P2 should have ha 7 cards from start");

        // Simulate turns until game ends
        int safetyCounter = 0;
        final int MAX_TURNS = 1000;

        while (!context.isGameOver()) {

            int currentPlayerIndex = context.getCurrentPlayerIndex();
            int opponentIndex = (currentPlayerIndex + 1) % 2; // Motståndaren

            GoFishUserPlayer currentPlayer = (GoFishUserPlayer) context.getCurrentPlayer();

            // Get rank from players first card
            String rank = currentPlayer.getHand().get(0).getRank();

            // Create PlayerAction to ask for a rank
            PlayerAction action = new PlayerAction(
                opponentIndex,
                "ASK",
                rank,
                null // Suit is not relevant
            );

            context.handleTurn(action); // handles turn

            // Safety check
            safetyCounter++;
            assertTrue(safetyCounter < MAX_TURNS,
                "possible inf gameloop, turncount right now is:  " + MAX_TURNS );
        }

        //Game should be over
        assertTrue(context.isGameOver(), "Game should finish when breaking game loop");

    }

    /**
     * Testing progress and books working correctly
     */
    @Test
    void gameProgressesAndPlayersGainPoints() {
        GameState state = new GameState();
        GoFishUserPlayer p1 = new GoFishUserPlayer("P1");
        GoFishUserPlayer p2 = new GoFishUserPlayer("P2");
        state.addPlayer(p1);
        state.addPlayer(p2);
        CardDeck deck = new CardDeck();
        GoFishRules rules = new GoFishRules(List.of(p1, p2), deck);
        GameContext context = new GameContext(state, rules);
        //Above is setup

        int turns = 0;
        final int MAX_TURNS = 500;

        while (!context.isGameOver() && turns < MAX_TURNS) {

            int currentPlayerIndex = context.getCurrentPlayerIndex();
            int opponentIndex = (currentPlayerIndex + 1) % 2;
            GoFishUserPlayer current = (GoFishUserPlayer) context.getCurrentPlayer();

            // Hand shouldnt be empty, this is handled by the rules
            if (current.getHand().isEmpty()) {
                break;
            }

            // Asking for first card in hand
            String rank = current.getHand().get(0).getRank();

            context.handleTurn(new PlayerAction(
                opponentIndex,
                "ASK",
                rank,
                null // Suit not relevant
            ));

            turns++;
        }

        // Verify points
        // After some turns someone should have at least one book
        assertTrue(
            p1.getPoints() > 0 || p2.getPoints() > 0,
            "After the game someone needs to have points"
        );
    }

}

