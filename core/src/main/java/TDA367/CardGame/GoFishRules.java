package TDA367.CardGame;
import java.util.ArrayList;
import java.util.List;

// 7 kort per spelare
// resten ligger i sjön
// Aktiva spelaren frågar "har du x"
//om motståndaren har kortet ger den ifrån sig det, om inte svarar man "finns i sjön, turen gåt vidare till nästa spelare"
//metoder som förväntas finnas i deck: -isEmpty(), shuffle(), deck.draw(),
//metoder i player klassen: add_card_to_hand(), has<-rank(), give_all_of_rank(), collect_books(),get_books_count()



public class GoFishRules {

    private final List<Player> players;
    private final Deck deck;
    private int current_player_index = 0;

    public GoFishRules(List<Player> players, Deck deck) {
        if (players.size() != 2) {
            throw new IllegalArgumentException("GofishGame require 2 players");
        }
        this.players = new ArrayList<>(players);
        this.deck = deck;
    }

    public void start_game() {
        deck.shuffle();
        int cards_per_player = 7;

        for (int i = 0; i < cards_per_player; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {
                    Card drawn = deck.draw();
                    player.add_card_to_hand(drawn);
                }
            }
        }

        for (Player player : players) {
            player.collect_books();
        }

        current_player_index = 0;
    }

    public boolean is_game_over() {
        return deck.isEmpty();
    }

    public void take_turn(Rank requestedRank) {
        Player current = players.get(current_player_index);
        Player opponent = players.get(1 - current_player_index);

        // Motståndaren har kort av denna rank
        if (opponent.has_rank(requestedRank)) {
            List<Card> taken = opponent.give_all_of_rank(requestedRank);
            for (Card c : taken) {
                current.add_card_to_hand(c);
            }
            current.collect_books();



        } else {

            // Finns i sjön
            if (!deck.isEmpty()) {
                Card drawn = deck.draw();
                current.add_card_to_hand(drawn);

                current.collect_books();
            }


            current_player_index = 1 - current_player_index;
        }
    }

    public Player get_winner() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);

        int score_1 = p1.get_book_count();
        int score_2 = p2.get_book_count();

        if (score_1 > score_2) {
            return p1;
        } else if (score_2 > score_1) {
            return p2;
        } else {
            return null; // equal / oavgjort
        }
    }
}

