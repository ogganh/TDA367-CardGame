package TDA367.CardGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private static Sound selectSound;


    public static void load(){
        selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/card_selection.wav"));
    }

    public static void playSelect(){
        selectSound.play();
    }
    public static void dispose(){
        selectSound.dispose();
    }
}
