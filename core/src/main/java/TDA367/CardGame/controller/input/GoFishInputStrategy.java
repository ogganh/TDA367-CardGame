package TDA367.CardGame.controller.input;

import TDA367.CardGame.View.Views.CardConversion;
import TDA367.CardGame.View.Views.GoFishInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import TDA367.CardGame.View.Views.ViewType;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.controller.PlayerAction;

public class GoFishInputStrategy implements InputStrategy {
    GameController gameController;
    private GoFishInterface view;
    private CardConversion cardConversion;

    public GoFishInputStrategy(GameController gameController, GoFishInterface view, CardConversion cardConversion) {
        this.gameController = gameController;
        this.view = view;
        this.cardConversion = cardConversion;
    }

    @Override
    public void handleInput() {
        if(view.IsAskedButtonClicked()){ //blev ask knappen klickad på?
            int cardIndex = view.GetSelectedCard(); //hämta den valda kortets index

            if (cardIndex != -1){
                String requestedRank = cardConversion.IntToRank(cardIndex);//gör om det till rank sträng, ex 0 = "ACE" osv
                int targetPlayerIndex = (gameController.getGameContext().getCurrentPlayerIndex()+1) % 2; //om current player är 0 så blir target 1 och vice versa
                //skapar en player action med vilken motståndare du frågar, actiontype "REQUEST", ranken du frågar efter och "......" som placeholder för kortet(som inte behövs här)
                gameController.getGameContext().handleTurn(new PlayerAction(targetPlayerIndex, "REQUEST", requestedRank, "......"));

                view.clearSelectedCard(); //knappen försvinner från vyn
                gameController.setCurrentView(ViewType.MIDDLE_SCREEN);//byter vy till mellan skärmen



            }
        }
    }
}
