package bomberman.constants;


public class GameConstants {


    public static final int TILE_SIZE = 32;

    public static int CANVAS_WIDTH = 1000;
    public static int CANVAS_HEIGHT = 500;

    public static int SCENE_WIDTH = 1000;
    public static int SCENE_HEIGHT = 500;

    public static int MENU_HEIGHT = 15;

    public static final String GAME_NAME = "Bomberman";

    public static int STEP_LENGTH = 2;

    public static int NUM_OF_COLUMNS = 30;
    public static int NUM_OF_ROWS = 30;

    public static int GRASS_LAYER = 0;
    public static int ITEM_LAYER = 1;
    public static int OBSTACLE_LAYER = 2;
    public static int BOMB_LAYER = 3;
    public static int FLAME_LAYER = 3;
    public static int ENEMY_LAYER = 4;
    public static int PLAYER_LAYER = 5;
    public static int MESSAGE_LAYER = 6;

    public static int AI_VIEW_RADIUS = 5;

    public static int PLAYER_LAG_TIME = 2000; // = 1s
    public static int ITEM_POWER_TIME = 10000; // = 10s

    public static int BOMB_WAITING_TIME = 1500; // 2s before exploding
    public static int BOMB_EXPLODING_TIME = 1000; // 1s exploding then dead

    public static int MESSAGE_TIME = 1500; // 5s before disappear, after enemy removed

    public static int ENEMY_DEAD_TIME = 1500;
    public static int PLAYER_DEAD_TIME = 1500;

}
