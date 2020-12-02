package bomberman.constants;

import java.io.File;

public class GameSounds {

    public static boolean mute = false;
    public static final String background = "/sound/";
    public static final String EXPLOSION = new File("src/main/resources/sound/explosion.wav").toURI().toString();
    public static final String BRICK_BREAK = "/sound/crate_break.wav";
    public static final String BOUNCE = "/sound/bounce.wav";
    public static final String POWER = "/sound/power01.wav";
    public static final String GAME_OVER = "/sound/gameOver.wav";


}
