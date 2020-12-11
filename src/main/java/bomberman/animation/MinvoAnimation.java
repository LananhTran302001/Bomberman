package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class MinvoAnimation {
    private static Image[] moveLeft = new Image[]{
            new Image(GameImages.MINVO_IMG + "_1.png"),
            new Image(GameImages.MINVO_IMG + "_2.png"),
            new Image(GameImages.MINVO_IMG + "_3.png")
    };
    private static Image[] moveRight = new Image[] {
            new Image(GameImages.MINVO_IMG + "_4.png"),
            new Image(GameImages.MINVO_IMG + "_5.png"),
            new Image(GameImages.MINVO_IMG + "_6.png")
    };

    private static Image[] dead = new Image[] {

            new Image(GameImages.MINVO_IMG + "_0.png"),
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
