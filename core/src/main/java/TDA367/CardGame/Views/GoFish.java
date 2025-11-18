package TDA367.CardGame.Views;

import TDA367.CardGame.UI.Button;
import TDA367.CardGame.UI.Card;
import TDA367.CardGame.UI.UIElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GoFish implements ViewInterface {
    List<UIElement> cardHand = new ArrayList<>();
    List<UIElement> opponentHand = new ArrayList<>();

    //TEMP
    float screenWidth = 495;
    float screenHeight = 270;
    Texture atlas;
    float cardSpace = 10;

    public GoFish() {
    }

    @Override
    public void CreateView() {
        atlas = new Texture("card_textures/1.2 Poker cards.png");

        //skapa initell position av alla kort
        //uiElementList.add(new Card(new Sprite(atlas, 0, 0 ,48,64)));
        //uiElementList.add(new Card(new Sprite(atlas, 48, 0 ,48,64)));
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 10; i++) {
                cardHand.add(new Card(new Sprite(atlas, 48 * i, 0 ,48,64)));

            }
        }
        for (int i = 0; i < 3; i++) {
            opponentHand.add(new Card(new Sprite(atlas, 0, 64 *4 ,48,64)));
        }

        //uiElementList.get(0).SetPosition(screenWidth / 2,0);

        //float margin = screenWidth / uiElementList.size();


    }
    Boolean check = true;
    int selected;
    Vector2 mousePosition = new Vector2(0,0);
    @Override
    public void Update() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            cardHand.add(new Card(new Sprite(atlas, 0, 0 ,48,64)));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            if (!cardHand.isEmpty()){
                cardHand.remove(cardHand.size()-1);

            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && check){
            selected ++;
            check = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && check){
            selected--;
            check = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&  !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            check = true;
        }

        selected = MathUtils.clamp(selected,0,cardHand.size() -1);


        selected = -1;

        // 48, 64 bred höjd av kort. temporär lös
        for (int i = 0; i < cardHand.size(); i++) {
            float xPos = CardsXposition(cardHand.size(), i);

            if (mousePosition.y < 64) {
                if (i == cardHand.size()-1) {
                    if (mousePosition.x > xPos && mousePosition.x < xPos + 48) {
                        selected = i;
                    }
                }
                else {
                    if (mousePosition.x > xPos && mousePosition.x < CardsXposition(cardHand.size(), i +1)) {
                        selected = i;
                    }
                }
            }


            if (i == selected){
                cardHand.get(i).SetPosition(
                    xPos,
                    30);
            }
            else {
                cardHand.get(i).SetPosition(
                    xPos,
                    0);
            }

        }
        for (int i = 0; i < opponentHand.size(); i++) {
            float xPos = CardsXposition(opponentHand.size(), i);
            opponentHand.get(i).SetPosition(xPos, screenHeight - 64);
        }

    }
    float CardsXposition(int amountOfCards, int index){
        float maxMargin = screenWidth / (cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards -1) * maxMargin, 0, screenWidth);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((screenWidth/2) - (handWidth /2) + margin * index,0 , screenWidth -48);
    }

    @Override
    public void MouseUpdate(Vector2 mousePosition) {
        this.mousePosition = mousePosition;

    }

    @Override
    public void Draw(SpriteBatch batch) {
        for (int i = 0; i < cardHand.size(); i++) {
            cardHand.get(i).Draw(batch);
        }
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).Draw(batch);
        }
    }
}
