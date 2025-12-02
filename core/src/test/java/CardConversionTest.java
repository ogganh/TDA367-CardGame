package TDA367.CardGame.View.Views;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardConversionTest {

    @Test
    public void testIntToRank_basicCases() {
        CardConversion conv = new CardConversion();

        assertEquals("ACE", conv.IntToRank(0));
        assertEquals("ACE", conv.IntToRank(13));
        assertEquals("ACE", conv.IntToRank(26));

        assertEquals("KING", conv.IntToRank(12));
        assertEquals("KING", conv.IntToRank(25));
        assertEquals("KING", conv.IntToRank(38));
    }

    @Test
    public void testCardToIntAndBack() {
        CardConversion conv = new CardConversion();

        int idx = conv.CardToInt("HEARTS", "TEN");
        assertEquals("HEARTS", conv.IntToSuit(idx));
        assertEquals("TEN", conv.IntToRank(idx));

        int idx2 = conv.CardToInt("SPADES", "QUEEN");
        assertEquals("SPADES", conv.IntToSuit(idx2));
        assertEquals("QUEEN", conv.IntToRank(idx2));
    }
}

