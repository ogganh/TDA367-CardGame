package TDA367.CardGame.View.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainView {
    public ViewInterface currentView;
    BitmapFont font;
    FitViewport viewPort;
    public MainView(BitmapFont font, FitViewport viewPort) {
        this.font = font;
        currentView = new StartView(font);
        currentView.CreateView();
        this.viewPort = viewPort;
    }

    public void StartView(){
        currentView = new StartView(font);
        currentView.CreateView();
    }
    public void GoFish(){
        currentView = new GoFish();
        currentView.CreateView();
    }
    public void Rules(){
        currentView = new RulesView(font);
        currentView.CreateView();
    }
    public void MiddleScreen(){
        currentView = new MiddleScreen(font);
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
