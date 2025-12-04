package TDA367.CardGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private static Sound hoverSound;
    private static Sound selectSound;

    public static void load(){
        hoverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hover.wav"));
        selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/selecting_card.wav"));
    }

    public static void playHover(){
        hoverSound.play();
    }

    public static void playSelect(){
        selectSound.play();
    }
    public static void dispose(){
        hoverSound.dispose();
        selectSound.dispose();
    }
}
