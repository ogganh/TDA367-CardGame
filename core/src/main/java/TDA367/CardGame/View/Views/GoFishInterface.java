package TDA367.CardGame.View.Views;

import java.util.List;

public interface GoFishInterface extends ViewInterface{
    void AddCards(List<Integer> cards);
    void AddCard(int index);
    void ResetHand();
    void SetOpponentscards(List<Integer> cards);
    void SelectCard();
    int GetSelectedCard();
    boolean IsAskedButtonClicked();
    void clearSelectedCard();
}
