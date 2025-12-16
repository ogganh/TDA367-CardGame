package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.Views.Games.GoFish;
import TDA367.CardGame.View.Views.Menus.EndScoreView;
import TDA367.CardGame.View.Views.Menus.GameSelectView;
import TDA367.CardGame.View.Views.Menus.StartView;
import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainView implements ViewController{
    private  ViewInterface currentView;

    private FitViewport viewPort;
    private GameController controller;
    private GameState state;

    /**
     * @param viewPort - The applications viewport.
     * @param state - The game state, used to update graphics according to the current state
     * @param controller - The game controller, used as middleware when passing input to the model
     */

    public MainView(FitViewport viewPort, GameState state) {
        currentView = new StartView(this);
        currentView.createView();
        this.viewPort = viewPort;

        this.state = state;
    }

    public void setController(GameController controller) { this.controller = controller; }

    public GameController getController() { return controller; }
    public GameState getState() { return state; }


    public void startView(){
        currentView = new StartView(this);
        currentView.createView();
    }
    public void goFish(){
        currentView = new GoFish(state, controller, this);
        currentView.createView();
        currentView.updateState();

    }
    public void rules(){
        currentView = new RulesView(this);
        currentView.createView();
    }
    public void middleScreen(){
        currentView = new MiddleScreen(this,state, controller);
        currentView.createView();
    }
    public void gameSelect(){
        currentView = new GameSelectView(this, controller);
        currentView.createView();
    }
    public void endScreen(){
        currentView = new EndScoreView(state, this);
        currentView.createView();
    }

    /**
     * Core logic loop, runs every frame.
     * Updates the mouse position and passes it to the current view.
     * Runs the update loop in the current view.
     */
    public void Update(){
        Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        Vector3 worldPosition = viewPort.unproject(cursorPosition);
        currentView.mouseUpdate(new Vector2(worldPosition.x, worldPosition.y));
        currentView.update();
    }
    /**
     * Updates the state
     */
    public void updateState(){currentView.updateState();}
    /**
     * The rendering of the view, could probably be moved into or (most likely better) called by MainView.update instead of GameController.update()
     */
    public void draw(SpriteBatch batch){
        viewPort.apply();
        batch.setProjectionMatrix(viewPort.getCamera().combined);
        batch.begin();
        currentView.draw(batch);
        batch.end();
    }

}
