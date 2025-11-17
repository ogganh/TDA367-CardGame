package TDA367.CardGame;
import java.util.ArrayList;
import java.util.List;

// 7 kort per spelare
// resten ligger i sjön
// Aktiva spelaren frågar "har du x"
//om motståndaren har kortet ger den ifrån sig det, om inte svarar man "finns i sjön, turen gåt vidare till nästa spelare"



public class GoFishRules {
    private final List<Player> players;
    private final Deck deck;
    private int current_player_index = 0;

    public GoFishRules(List<Player> players, Deck deck){
        if (players.size()!=2) {
            throw new IllegalArgumentException("Go fish Game needs 2 players");
        }
        this.players = new ArrayList<>(players);
        this.deck = deck;

    }

    public void start_game() {
        deck.shuffle(); //måste finnas i deck
        int cards_per_player = 7;

        for (int i = 0; i < cards_per_player; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {   //från deck klassen?
                    Card drawn = deck.draw(); //finnas i deck
                    player.add_card_to_hand(drawn);  // en metod i plyer klassen?
                }
            }
        }

        current_player_index = 0;


    }







}
