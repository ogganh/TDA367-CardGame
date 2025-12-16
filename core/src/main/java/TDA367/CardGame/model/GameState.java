package TDA367.CardGame.model;

import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.gameLogic.strategies.TurnManager;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.player.UserPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/** GameState keeps track of registered players, instances of card piles, and the current player.
 * To support multiple card games, a HashMap is used to store different card piles by name.
 */

public class GameState {

    private Map<String, CardDeck> piles = new HashMap<>();
    private List<UserPlayer> players = new ArrayList<>();
    private TurnManager turnManager;
    private boolean middleScreen = false;

    public void addPile(String name, CardDeck pile) {piles.put(name, pile);}
    public void addPlayer(UserPlayer player) {players.add(player);}
    public void closeMiddleScreen() {middleScreen = false;}
    public void openMiddleScreen() {middleScreen = true;}
    public boolean isMiddleScreenOpen() {return middleScreen;}
    public CardDeck getPile(String name) {return piles.get(name);}
    public List<UserPlayer> getPlayers() {return players;}
    public void setTurnManager(TurnManager turnManager) {this.turnManager = turnManager;}
    public int getCurrentPlayer() { if (turnManager == null) {
        throw new IllegalStateException("TurnManager not set");
        }
        return turnManager.getCurrentIndex();
    }

    public int getBookCount(int playerIndex) {
        UserPlayer p = players.get(playerIndex);
        if (p instanceof GoFishUserPlayer) {
            return ((GoFishUserPlayer) p).getPoints(); // returns the number of books a player has
        }
        return 0;
    }


    public void reset() {
        piles = new HashMap<>();
        players = new ArrayList<>();
        middleScreen = false;
        turnManager = null;
    }
}

