import java.util.HashMap;

public class CardTests {

    public static void main(String[] args) {
        System.out.println("Running Card Tests...");
        testCardCreation();
    }

    public static void testCardCreation() {
        Card card = new Card("SPADES", "ACE");
        try {
            assert card.suit.equals("HEARTS");
            assert card.rank.equals("ACE");
        } catch (AssertionError e) {
            System.out.println("testCardCreation failed");
        }
    }
}
