package TDA367.CardGame.View.Views;

import TDA367.CardGame.controller.GameState;
import TDA367.CardGame.controller.ViewController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainView {
    public ViewInterface currentView;

    FitViewport viewPort;
    public MainView(FitViewport viewPort) {
        this.viewPort = viewPort;
    }

    public void StartView(ViewController view){
        currentView = new StartView(view);
        currentView.CreateView();
    }
    public void GoFish(GameState state){
        currentView = new GoFish(state);
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
