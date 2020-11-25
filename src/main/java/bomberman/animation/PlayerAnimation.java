package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class PlayerAnimation {

    private static Image[] moveRight = new Image[] {
            new Image(GameImages.PLAYER_IMG + "_right.png"),
            new Image(GameImages.PLAYER_IMG + "_right_01.png"),
            new Image(GameImages.PLAYER_IMG + "_right_02.png"),
            new Image(GameImages.PLAYER_IMG + "_right_03.png")
    };

    private static Image[] moveLeft = new Image[] {
            new Image(GameImages.PLAYER_IMG + "_left.png"),
            new Image(GameImages.PLAYER_IMG + "_left_01.png"),
            new Image(GameImages.PLAYER_IMG + "_left_02.png"),
            new Image(GameImages.PLAYER_IMG + "_left_03.png")
    };

    private static Image[] moveDown = new Image[] {
            new Image(GameImages.PLAYER_IMG + "_down.png"),
            new Image(GameImages.PLAYER_IMG + "_down_01.png"),
            new Image(GameImages.PLAYER_IMG + "_down_02.png"),
            new Image(GameImages.PLAYER_IMG + "_down_01.png")
    };

    private static Image[] moveUp = new Image[] {
            new Image(GameImages.PLAYER_IMG + "_up.png"),
            new Image(GameImages.PLAYER_IMG + "_up_01.png"),
            new Image(GameImages.PLAYER_IMG + "_up_02.png"),
            new Image(GameImages.PLAYER_IMG + "_up_01.png")
    };

    public static Image[] getMoveRight() {
        return moveRight;
    }

    public static Image[] getMoveLeft() {
        return moveLeft;
    }

    public static Image[] getMoveDown() {
        return moveDown;
    }

    public static Image[] getMoveUp() {
        return moveUp;
    }

    public static Image getDie() {
        return new Image(GameImages.PLAYER_IMG + "_died.png");
    }

}
