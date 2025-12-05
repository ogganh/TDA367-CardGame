package TDA367.CardGame.model.player;
import TDA367.CardGame.model.card_logic.Card;

import java.util.List;

/**
 * Interface for a player in the card game.
 */

public interface IPlayer {
    String get_name();
    void add_card(Card card);
    void remove_card(Card card);
    List<Card> get_hand();
}