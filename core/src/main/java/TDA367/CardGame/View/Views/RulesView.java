package TDA367.CardGame.View.Views;

import TDA367.CardGame.Main;
import TDA367.CardGame.View.UI.Button;
import TDA367.CardGame.View.UI.ButtonAction;
import TDA367.CardGame.View.UI.Text;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RulesView implements ViewInterface {
    private Text text;
    private Button back;
    private MainView mainView;

    public RulesView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void CreateView() {

        // Create Guess button
        back = new Button(
            ViewInformation.font,
            "Back",
            new Sprite(ViewInformation.uiAtlas, 32, 0, 16, 16));

        // Add a "on click" function to the guess button
        back.ChangeAction(new ButtonAction() {
            @Override
            public void Action() {
                // Send the input to the controller if a card is selected
                mainView.GoFish();
            }
        });

        back.SetScale(3, 2);
        back.SetPosition(30,ViewInformation.screenSize.y -20);

        //ViewInformation.font.getData().setScale(0.3f);
        BitmapFont txtFont = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"), false);;
        txtFont.getData().setScale(0.3f);
        text = new Text(txtFont,
            "Rules : Go Fish\n" +
                "\n" +
                "Start:\n" +
                "   - Each player begins with 7 cards.\n" +
                "   - The remaining cards are placed in the draw pile, the “pond”.\n" +
                "\n" +
                "Your Turn:\n" +
                "   - You may ask any player for a rank you have in your hand, e.g. “Do you have any Kings?”\n" +
                "   - If the player has cards of that rank, they must give you all of them and you continue your turn.\n" +
                "\n" +
                "Go Fish:\n" +
                "   - If the player does not have the rank, they respond with “Go Fish!”\n" +
                "   - You then draw 1 card from the pond.\n" +
                "   - After drawing, your turn always ends and the next player takes their turn.\n" +
                "\n" +
                "How to Win:\n" +
                "   - Collect four cards of the same rank to complete a set.\n" +
                "   - Each set is worth 1 point.\n" +
                "   - The player with the most sets wins the game.\n" +
                "\n" +
                "End of the Game:\n" +
                "   - The game ends when the pond is empty and all sets have been collected.\n" +
                "\n" +
                "\n");
        text.SetPosition(60, ViewInformation.screenSize.y - 20);

    }

    @Override
    public void Update() {

    }

    @Override
    public void UpdateState() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        back.MouseUpdate(mousePosition);
    }

    @Override
    public void Draw(SpriteBatch batch) {
        text.Draw(batch);
        back.Draw(batch);
    }
}
