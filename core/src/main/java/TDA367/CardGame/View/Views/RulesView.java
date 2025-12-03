package TDA367.CardGame.View.Views;

import TDA367.CardGame.View.UI.Text;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RulesView implements ViewInterface {
    Text text;

    public RulesView() {

    }

    @Override
    public void CreateView() {
        ViewInformation.font.getData().setScale(0.3f);
        text = new Text(ViewInformation.font,
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
        text.SetPosition(0, ViewInformation.screenSize.y);

    }

    @Override
    public void Update() {

    }

    @Override
    public void UpdateState() {

    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {

    }

    @Override
    public void Draw(SpriteBatch batch) {
        text.Draw(batch);
    }
}
