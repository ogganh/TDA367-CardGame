package TDA367.CardGame.model.gameLogic.strategies;
import TDA367.CardGame.model.card_logic.Card;

import java.util.ArrayList;
import java.util.List;

public class TrickManager {
    private List<Card> currentTrick;
    private String leadSuit;
    private int fisrtPlayerInTrickIndex;
    private final int playersCount;

    public TrickManager(int playersCount){
        this.playersCount = playersCount;
        this.currentTrick = new ArrayList<>();
        this.leadSuit = null;
        this.fisrtPlayerInTrickIndex = -1;
    }



}
