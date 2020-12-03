package bomberman.constants;

import javafx.scene.media.MediaPlayer;

import java.io.File;

public class GameSounds {

    public static boolean mute = false;
    public static final String EXPLOSION = new File("src/main/resources/sound/explosion.mp3").toURI().toString();
    public static final String BREAK = new File("src/main/resources/sound/break.mp3").toURI().toString();
    public static final String PICK_ITEM = new File("src/main/resources/sound/bomber_pick_item.mp3").toURI().toString();
    public static final String GAME_OVER = "src/main/resources/sound/gameOver.wav";
    public static final String REACH_PORTAL = new File("src/main/resources/sound/reachPortal.mp3").toURI().toString();
    public static final String PLANT_BOMB = new File("src/main/resources/sound/plant_bomb.mp3").toURI().toString();
    public static final String PAUSE = new File("src/main/resources/sound/pause.mp3").toURI().toString();
    public static final String ERROR = new File("src/main/resources/sound/menu_error.mp3").toURI().toString();
    public static final String PLAYER_SHOCK = new File("src/main/resources/sound/sick_1.mp3").toURI().toString();
    public static final String PLAYER_DEAD = new File("src/main/resources/sound/bomber_death.mp3").toURI().toString();
    public static final String ENEMY_DEAD = new File("src/main/resources/sound/item_fumes.mp3").toURI().toString();

    public static void playSound(MediaPlayer mediaPlayer) {
        if (!mute) {
            mediaPlayer.setVolume(0.05);
            mediaPlayer.play();
        }
    }


}
