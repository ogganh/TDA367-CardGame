package TDA367.CardGame.model.card_logic;

public enum Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public static int rankToValue(String rank) {
        switch (rank) {
            case "TWO":
                return 2;
            case "THREE":
                return 3;
            case "FOUR":
                return 4;
            case "FIVE":
                return 5;
            case "SIX":
                return 6;
            case "SEVEN":
                return 7;
            case "EIGHT":
                return 8;
            case "NINE":
                return 9;
            case "TEN":
                return 10;
            case "JACK":
                return 11;
            case "QUEEN":
                return 12;
            case "KING":
                return 13;
            case "ACE":
                return 14;
            default:
                throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }
}
