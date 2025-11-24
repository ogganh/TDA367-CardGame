package TDA367.CardGame.model;

import TDA367.CardGame.model.card_logic.CardDeck;
import TDA367.CardGame.model.player.UserPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class GameState {
    private Map<String, CardDeck> piles = new HashMap<>();
    private List<UserPlayer> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void addPile(String name, CardDeck pile) {
        piles.put(name, pile);
    }

    public CardDeck getPile(String name) {
        return piles.get(name);
    }

    public void addPlayer(UserPlayer player) {
        players.add(player);
    }

    public List<UserPlayer> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}