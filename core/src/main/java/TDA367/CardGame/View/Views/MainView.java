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

public class MainView {
    public ViewInterface currentView;

    FitViewport viewPort;
    private GameController controller;
    private GameState state;

    /**
     * @param viewPort - The applications viewport.
     * @param state - The game state, used to update graphics according to the current state
     * @param controller - The game controller, used as middleware when passing input to the model
     */

    public MainView(FitViewport viewPort, GameState state, GameController controller) {
        currentView = new StartView(this);
        currentView.CreateView();
        this.viewPort = viewPort;

        this.controller = controller;
        this.state = state;
    }

    public GameController getController() { return controller; }
    public GameState getState() { return state; }


    public void StartView(){
        currentView = new StartView(this);
        currentView.CreateView();
    }
    public void GoFish(){
        currentView = new GoFish(state, controller, this);
        currentView.CreateView();
        currentView.UpdateState();

    }
    public void Rules(){
        currentView = new RulesView();
        currentView.CreateView();
    }
    public void MiddleScreen(){
        currentView = new MiddleScreen(state, controller);
        currentView.CreateView();
    }
    public void GameSelect(){
        currentView = new GameSelectView(this);
        currentView.CreateView();
    }
    public void EndScreen(){
        currentView = new EndScoreView(state, this);
        currentView.CreateView();
    }

    /**
     * Core logic loop, runs every frame.
     * Updates the mouse position and passes it to the current view.
     * Runs the update loop in the current view.
     */
    public void Update(){
        Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        Vector3 worldPosition = viewPort.unproject(cursorPosition);
        currentView.MouseUpdate(new Vector2(worldPosition.x, worldPosition.y));
        currentView.Update();
    }
    /**
     * Updates the state
     */
    public void UpdateState(){currentView.UpdateState();}
    /**
     * The rendering of the view, could probably be moved into or (most likely better) called by MainView.Update instead of GameController.update()
     */
    public void Draw(SpriteBatch batch){
        viewPort.apply();
        batch.setProjectionMatrix(viewPort.getCamera().combined);
        batch.begin();
        currentView.Draw(batch);
        batch.end();
    }

}
