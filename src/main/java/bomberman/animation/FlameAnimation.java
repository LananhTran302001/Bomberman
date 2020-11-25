package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class FlameAnimation {
    /*
            3
            1
        4 2 0 2 6
            1
            5
     */

    private static Image[] weakFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_00.png"), // index 0
            new Image(GameImages.FLAME_IMG + "_12.png"), // index 1
            new Image(GameImages.FLAME_IMG + "_03.png"), // index 2
            new Image(GameImages.FLAME_IMG + "_16.png"), // index 3
            new Image(GameImages.FLAME_IMG + "_04.png"), // index 4
            new Image(GameImages.FLAME_IMG + "_13.png"), // index 5
            new Image(GameImages.FLAME_IMG + "_07.png")  // index 6

    };
    private static Image[] mediumFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_01.png"), // index 0
            new Image(GameImages.FLAME_IMG + "_19.png"), // index 1
            new Image(GameImages.FLAME_IMG + "_10.png"), // index 2
            new Image(GameImages.FLAME_IMG + "_17.png"), // index 3
            new Image(GameImages.FLAME_IMG + "_05.png"), // index 4
            new Image(GameImages.FLAME_IMG + "_14.png"), // index 5
            new Image(GameImages.FLAME_IMG + "_08.png")  // index 6

    };

    private static Image[] bigFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_02.png"), // index 0
            new Image(GameImages.FLAME_IMG + "_20.png"), // index 1
            new Image(GameImages.FLAME_IMG + "_11.png"), // index 2
            new Image(GameImages.FLAME_IMG + "_18.png"), // index 3
            new Image(GameImages.FLAME_IMG + "_06.png"), // index 4
            new Image(GameImages.FLAME_IMG + "_15.png"), // index 5
            new Image(GameImages.FLAME_IMG + "_09.png")  // index 6

    };


    public static Image[] getWeakFlame() {
        return weakFlame;
    }

    public static Image[] getMediumFlame() {
        return mediumFlame;
    }

    public static Image[] getBigFlame() {
        return bigFlame;
    }
}
