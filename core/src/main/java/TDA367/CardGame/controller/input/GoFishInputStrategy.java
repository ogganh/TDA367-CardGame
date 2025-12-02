package TDA367.CardGame.controller.input;

import TDA367.CardGame.View.Views.ViewInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import TDA367.CardGame.View.Views.ViewType;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.controller.PlayerAction;

public class GoFishInputStrategy implements InputStrategy {
    GameController gameController;
    ViewInterface view;
    public GoFishInputStrategy(GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void handleInput() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            // input -> gofishView (GoFish.click()) ->
            // antingen: select (viewn sköter det)
            // eller: guess -> gofishcontroller.guess(int i)


            //String requestedRank = "ACE"; // For simplicity, we hardcode the requested rank
            //String requestedSuit = "SPADES"; // Hardcoded suit, not used in Go Fish
            //int targetPlayerIndex = (gameController.getGameContext().getCurrentPlayerIndex()+1) % 2;

            //Gdx.app.log("GoFishRules", gameController.getGameContext().getCurrentPlayer().get_name() + " asked " + gameController.getGameContext().getState().getPlayers().get((gameController.getGameContext().getCurrentPlayerIndex()+1) % 2).get_name() + " for rank " + requestedRank);
            //gameController.getGameContext().handleTurn(new PlayerAction(targetPlayerIndex, "REQUEST", requestedRank, requestedSuit));

            //gameController.setCurrentView(ViewType.MIDDLE_SCREEN);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameController.setCurrentView(ViewType.RULES);
        }


    }
}
