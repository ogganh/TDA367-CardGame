package TDA367.CardGame.model;

public class PlumpGuessAction extends PlayerAction {
    private final int guessValue;

    public PlumpGuessAction(int playerIndex, int guessValue){
        super(playerIndex, "GUESS", null, null);
        this.guessValue = guessValue;
    }

    public int guessValue(){
        return guessValue;
    }
}
