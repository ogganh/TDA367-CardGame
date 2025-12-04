package TDA367.CardGame.model;

import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.player.GoFishUserPlayer;
import TDA367.CardGame.model.player.UserPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class GameState {
    private Map<String, CardDeck> piles = new HashMap<>();
    private List<UserPlayer> players = new ArrayList<>();
    private int currentPlayer = 0;

    public void addPile(String name, CardDeck pile) {
        piles.put(name, pile);
    }
    public void addPlayer(UserPlayer player) {
        players.add(player);
    }
    public void SetCurrentPlayer(int player){ currentPlayer = player;}

    public CardDeck getPile(String name) {
        return piles.get(name);
    }
    public List<UserPlayer> getPlayers() {
        return players;
    }
    public int GetCurrentPlayer(){return currentPlayer;}
    public int getBookCount(int playerIndex) {
        UserPlayer p = players.get(playerIndex);
        if (p instanceof GoFishUserPlayer) {
            return ((GoFishUserPlayer) p).get_points();//returnerar antal böcker en spelare har
        }
        return 0;
    }


    public void reset() {
        piles = new HashMap<>();
        players = new ArrayList<>();
        currentPlayer = 0;
    }
}
