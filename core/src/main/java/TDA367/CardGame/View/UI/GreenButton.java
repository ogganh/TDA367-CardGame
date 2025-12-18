package TDA367.CardGame.View.UI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import TDA367.CardGame.View.ViewInformation;

class GreenButton extends Button {
    /**
     * Creates a green button
     */
    public GreenButton(BitmapFont font, String text, ButtonAction action) {
        super(font, text, new Sprite(ViewInformation.greenButtonTexture), action);
        this.sprite.setSize(14, 15);
        this.sprite.setOriginCenter();
    }
    
}
