package TDA367.CardGame.View.UI.cards;

import TDA367.CardGame.View.UI.UIElement;
import TDA367.CardGame.View.UI.UIElementFactory;
import TDA367.CardGame.View.ViewInformation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class OpponentHand {
    private List<UIElement> opponentHand = new ArrayList<>();


    public void update(int amountOfCards){
        opponentHand.clear();
        for (int i = 0; i < amountOfCards; i++) {
            opponentHand.add(UIElementFactory.createCard(new Sprite(ViewInformation.cardAtlas, 0, ViewInformation.cardHeight * 4, ViewInformation.cardWidth, ViewInformation.cardHeight), -1));
        }

        for (int i = 0; i < opponentHand.size(); i++) {
            float xPos = CardsXPosition(opponentHand.size(), i);

            //opponentHand.get(i).setOrigin(-pos.x + ViewInformation.screenSize.x / 2, -pos.y + ViewInformation.screenSize.y / 2);
            //opponentHand.get(i).setRotation(angle);
            opponentHand.get(i).setPosition(xPos, ViewInformation.screenSize.y - opponentHand.get(i).getSize().y - ViewInformation.cardYPos);
        }
    }
    public void draw(SpriteBatch batch){
        for (int i = 0; i < opponentHand.size(); i++) {
            opponentHand.get(i).draw(batch);
        }
    }
    public void resetHand() {
        opponentHand.clear();
    }
    float CardsXPosition(int amountOfCards, int index) {
        float maxMargin = ViewInformation.screenSize.x / (ViewInformation.cardSpace + amountOfCards);
        float handWidth = MathUtils.clamp((amountOfCards) * maxMargin, 0, ViewInformation.screenSize.x);
        float margin = handWidth / amountOfCards;
        return MathUtils.clamp((ViewInformation.screenSize.x / 2) - (handWidth / 2) + margin * index, 0, ViewInformation.screenSize.x - ViewInformation.cardWidth);
    }
}
