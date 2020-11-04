package bomberman.constants;

import java.util.ArrayList;
import java.util.List;

public class GameConstants {

    public static int TILE_SIZE = 40;
    public static String GRASS_IMG = "/image/textureImg/grass";
    public static String WALL_IMG = "/image/textureImg/wall.jpg";
    public static String PORTAL_IMG = "/image/textureImg/portal01.png";
    public static String BRICK_IMG = "/image/textureImg/land.jpg";
    public static String BOMB_IMG = "/image/bmb01.png";

    public static int CANVAS_WIDTH = 640;
    public static int CANVAS_HEIGHT = 640;

    public static int SCENE_WIDTH = 640;
    public static int SCENE_HEIGHT = 640;

    public static String GAME_NAME = "Bomberman";

    public static int STEP_LENGTH = 20;


    private static final String playerPath = "/image/playerImg/player";
    public static List<String> getPlayerImageUrl(Direction direction) {

        String dir = "";
        switch (direction) {
            case UP:
                dir = "U";
                break;

            case DOWN:
                dir = "D";
                break;

            case LEFT:
                dir = "L";
                break;

            case RIGHT:
                dir = "R";
                break;
        }
        List<String> urls = new ArrayList<String>();
        urls.add(playerPath + dir + "1.png");
        urls.add(playerPath + dir + "2.png");
        return urls;

    }

}
