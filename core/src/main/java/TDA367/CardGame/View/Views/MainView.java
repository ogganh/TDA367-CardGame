package TDA367.CardGame.View.Views;

import TDA367.CardGame.controller.GameController;
import TDA367.CardGame.model.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainView {
    public ViewInterface currentView;

    FitViewport viewPort;
    private static MainView instance;
    private GameController controller;
    private GameState state;
    

    public MainView(FitViewport viewPort, GameState state, GameController controller) {
        currentView = new StartView(this);
        currentView.CreateView();
        this.viewPort = viewPort;
        instance = this;

        this.controller = controller;
        this.state = state;
    }

    public GameController getController() { return controller; }

    public static MainView getInstance() {
        return instance;
    }
    public void StartView(){
        currentView = new StartView(this);
        currentView.CreateView();
    }
    public void GoFish(){
        currentView = new GoFish(state, controller);
        currentView.CreateView();
    }
    public void Rules(){
        currentView = new RulesView();
        currentView.CreateView();
    }
    public void MiddleScreen(){
        currentView = new MiddleScreen();
        currentView.CreateView();
    }

    public void Update(){
        Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        Vector3 worldPosition = viewPort.unproject(cursorPosition);

        currentView.MouseUpdate(new Vector2(worldPosition.x, worldPosition.y));
        currentView.Update();
    }
    public void Draw(SpriteBatch batch){
        viewPort.apply();
        batch.setProjectionMatrix(viewPort.getCamera().combined);
        batch.begin();
        currentView.Draw(batch);
        batch.end();
    }

}
