package TDA367.CardGame.View.UI.cards;

import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.UI.UIElementFactory;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class OpponentHand {
    List<UIElement> opponentHand = new ArrayList<>();


    public void Update(int amountOfCards){
        opponentHand.clear();
        for (int i = 0; i < amountOfCards; i++) {
            opponentHand.add(UIElementFactory.CreateCard(new Sprite(ViewInformation.cardAtlas, 0, ViewInformation.cardHeight * 4, ViewInformation.cardWidth, ViewInformation.cardHeight), -1));
        }

        for (int i = 0; i < opponentHand.size(); i++) {
            float xPos = CardsXPosition(opponentHand.size(), i);
            Vector2 pos = opponentHand.get(i).GetPosition();

            //opponentHand.get(i).SetOrigin(-pos.x + ViewInformation.screenSize.x / 2, -pos.y + ViewInformation.screenSize.y / 2);
            //opponentHand.get(i).SetRotation(angle);
            opponentHand.get(i).SetPosition(xPos, ViewInformation.screenSize.y - opponentHand.get(i).GetSize().y - ViewInformation.cardYPos);
        }
    }
    public void Draw(SpriteBatch batch){
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).Draw(batch);
        }
    }
    public void ResetHand() {
        opponentHand.clear();
    }
    float CardsXPosition(int amountOfCards, int index) {
        float maxMargin = ViewInformation.screenSize.x / (ViewInformation.cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, ViewInformation.screenSize.x);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((ViewInformation.screenSize.x / 2) - (handWidth / 2) + margin * index, 0, ViewInformation.screenSize.x - ViewInformation.cardWidth);
    }
}
