package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class BalloomAnimation {
    private static Image[] moveLeft = new Image[]{
            new Image(GameImages.BALLOOM_IMG + "_left_01.png"),
            new Image(GameImages.BALLOOM_IMG + "_left_02.png"),
            new Image(GameImages.BALLOOM_IMG + "_left_03.png")
    };
    private static Image[] moveRight = new Image[] {
            new Image(GameImages.BALLOOM_IMG + "_right_01.png"),
            new Image(GameImages.BALLOOM_IMG + "_right_02.png"),
            new Image(GameImages.BALLOOM_IMG + "_right_03.png")
    };
    private static Image[] dead = new Image[] {
            new Image(GameImages.BALLOOM_IMG + "_dead.png"),
            new Image(GameImages.BALLOOM_IMG + "_dead.png"),
            new Image(GameImages.BALLOOM_IMG + "_dead.png"),
            new Image(GameImages.BALLOOM_IMG + "_dead_01.png"),
            new Image(GameImages.BALLOOM_IMG + "_dead_02.png"),
            new Image(GameImages.BALLOOM_IMG + "_dead_03.png")
    };

    public static Image[] getMoveLeft() {
        return moveLeft;
    }

    public static Image[] getMoveRight() {
        return moveRight;
    }

    public static Image[] getDead() {
        return dead;
    }
}
