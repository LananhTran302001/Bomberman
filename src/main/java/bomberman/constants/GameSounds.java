package bomberman.constants;

import java.io.File;

public class GameSounds {

    public static final String PORTAL = new File("src/main/resources/sound/reachPortal.mp3").toURI().toString();

    public static final String BREAK = new File("src/main/resources/sound/break.mp3").toURI().toString();

    public static final String PICK_ITEM = new File("src/main/resources/sound/bomber_pick_item.mp3").toURI().toString();

    public static final String PLANT_BOMB = new File("src/main/resources/sound/plant_bomb.mp3").toURI().toString();
    public static final String EXPLOSION = new File("src/main/resources/sound/explosion.mp3").toURI().toString();

    public static final String PLAYER_SHOCK = new File("src/main/resources/sound/sick_1.mp3").toURI().toString();
    public static final String PLAYER_DEAD = new File("src/main/resources/sound/bomber_death.mp3").toURI().toString();
    public static final String PLAYER_STEP = new File("src/main/resources/sound/step_03.mp3").toURI().toString();

    public static final String ENEMY_DEAD = new File("src/main/resources/sound/bomber_punch.mp3").toURI().toString();

    public static final String PAUSE = new File("src/main/resources/sound/pause.mp3").toURI().toString();
    public static final String ERROR = new File("src/main/resources/sound/menu_error.mp3").toURI().toString();

    public static final String LEVEL_START = new File("src/main/resources/sound/LevelStart.mp3").toURI().toString();
    public static final String GAME_OVER = new File("src/main/resources/sound/GameOver.mp3").toURI().toString();
    public static final String STAGE_1 = new File("src/main/resources/sound/Stage_01.mp3").toURI().toString();
    public static final String STAGE_2 = new File("src/main/resources/sound/Stage_02.mp3").toURI().toString();


}
