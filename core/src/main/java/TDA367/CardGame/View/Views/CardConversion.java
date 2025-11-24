package TDA367.CardGame.View.Views;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CardConversion {
    Dictionary<String,Integer> suitToInt = new Hashtable<>();
    Dictionary<String,Integer> rankToInt = new Hashtable<>();
    List<String> suits = new ArrayList<>();
    List<String> ranks = new ArrayList<>();


    public CardConversion() {
        suits.add("HEARTS");
        suits.add("DIAMONDS");
        suits.add("SPADES");
        suits.add("CLUBS");

        ranks.add("ACE");
        ranks.add("TWO");
        ranks.add("THREE");
        ranks.add("FOUR");
        ranks.add("FIVE");
        ranks.add("SIX");
        ranks.add("SEVEN");
        ranks.add("EIGHT");
        ranks.add("NINE");
        ranks.add("TEN");
        ranks.add("JACK");
        ranks.add("QUEEN");
        ranks.add("KING");

        suitToInt.put("HEARTS" , 0);
        suitToInt.put("DIAMONDS" , 13);
        suitToInt.put("SPADES" , 26);
        suitToInt.put("CLUBS" , 39);

        rankToInt.put("ACE", 0);
        rankToInt.put("TWO", 1);
        rankToInt.put("THREE", 2);
        rankToInt.put("FOUR", 3);
        rankToInt.put("FIVE", 4);
        rankToInt.put("SIX", 5);
        rankToInt.put("SEVEN", 6);
        rankToInt.put("EIGHT", 7);
        rankToInt.put("NINE", 8);
        rankToInt.put("TEN", 9);
        rankToInt.put("JACK", 10);
        rankToInt.put("QUEEN", 11);
        rankToInt.put("KING", 12);
    }
    public int CardToInt(String suit, String rank){
        return suitToInt.get(suit) + rankToInt.get(rank);
    }
    public String IntToSuit(int index){
        int suit = index / 13;
        return suits.get(suit);
    }
    public String IntToRank(int index){
        int rank = index % 13;
        return ranks.get(rank);
    }
}
