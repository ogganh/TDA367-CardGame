package TDA367.CardGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/** Methods for enabling and disabling music and sound effects. */

public class SoundManager {

    private static Sound hoverSound;
    private static Sound selectSound;
    private static Music backgroundMusic;

    public static void load(){
        hoverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hover.wav"));
        selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/selecting_card.wav"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/bg_music.mp3"));

    }

    public static void playHover(){
        hoverSound.play();
    }

    public static void playSelect(){
        selectSound.play();
    }

    public static void playBGMusic(){
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    public static void dispose(){
        hoverSound.dispose();
        selectSound.dispose();
    }
}
