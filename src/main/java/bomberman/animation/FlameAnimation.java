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

    // FLAME 0
    private static Image[] centerFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_00.png"), // index 0
            new Image(GameImages.FLAME_IMG + "_01.png"), // index 0
            new Image(GameImages.FLAME_IMG + "_02.png")  // index 0
    };

    // FLAME 1
    private static Image[] verticalFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_12.png"),
            new Image(GameImages.FLAME_IMG + "_19.png"),
            new Image(GameImages.FLAME_IMG + "_20.png")
    };

    // FLAME 2
    private static Image[] horizontalFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_03.png"),
            new Image(GameImages.FLAME_IMG + "_10.png"),
            new Image(GameImages.FLAME_IMG + "_11.png")
    };

    /*
            3
            1
        4 2 0 2 6
            1
            5
     */

    // FLAME 3
    private static Image[] topFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_16.png"),
            new Image(GameImages.FLAME_IMG + "_17.png"),
            new Image(GameImages.FLAME_IMG + "_18.png")
    };

    // FLAME 4
    private static Image[] leftFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_04.png"),
            new Image(GameImages.FLAME_IMG + "_05.png"),
            new Image(GameImages.FLAME_IMG + "_06.png")
    };

    // FLAME 5
    private static Image[] bottomFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_13.png"),
            new Image(GameImages.FLAME_IMG + "_14.png"),
            new Image(GameImages.FLAME_IMG + "_15.png")
    };

    // FLAME 6
    private static Image[] rightFlame = new Image[] {
            new Image(GameImages.FLAME_IMG + "_07.png"),
            new Image(GameImages.FLAME_IMG + "_08.png"),
            new Image(GameImages.FLAME_IMG + "_09.png")
    };

    /*
            3
            1
        4 2 0 2 6
            1
            5
     */

    public static Image[] getCenterFlame() {
        return centerFlame;
    }

    public static Image[] getHorizontalFlame() {
        return horizontalFlame;
    }

    public static Image[] getVerticalFlame() {
        return verticalFlame;
    }

    public static Image[] getTopFlame() {
        return topFlame;
    }

    public static Image[] getBottomFlame() {
        return bottomFlame;
    }

    public static Image[] getLeftFlame() {
        return leftFlame;
    }

    public static Image[] getRightFlame() {
        return rightFlame;
    }
}
