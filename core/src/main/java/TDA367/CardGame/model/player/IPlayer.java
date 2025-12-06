package TDA367.CardGame.model.player;
import TDA367.CardGame.model.card_logic.Card;

import java.util.List;

/**
 * Interface for a player in the card game.
 */

public interface IPlayer {
    String getName();
    void addCard(Card card);
    void removeCard(Card card);
    List<Card> getHand();
}