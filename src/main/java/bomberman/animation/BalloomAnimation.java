package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class BalloomAnimation {
    private Image[] moveLeft = new Image[3];
    private Image[] moveRight = new Image[3];
    private Image[] dead = new Image[4];

    public BalloomAnimation() {
        moveLeft[0] = new Image(GameImages.BALLOOM_IMG + "_left_01.png");
        moveLeft[1] = new Image(GameImages.BALLOOM_IMG + "_left_02.png");
        moveLeft[2] = new Image(GameImages.BALLOOM_IMG + "_left_03.png");

        moveRight[0] = new Image(GameImages.BALLOOM_IMG + "_right_01.png");
        moveRight[1] = new Image(GameImages.BALLOOM_IMG + "_right_02.png");
        moveRight[2] = new Image(GameImages.BALLOOM_IMG + "_right_03.png");
        moveRight[3] = new Image(GameImages.BALLOOM_IMG + "_right_04.png");

        dead[0] = new Image(GameImages.BALLOOM_IMG + "_dead.png");
        dead[1] = new Image(GameImages.BALLOOM_IMG + "_dead_01.png");
        dead[2] = new Image(GameImages.BALLOOM_IMG + "_dead_02.png");
        dead[3] = new Image(GameImages.BALLOOM_IMG + "_dead_03.png");
    }

    public Image[] getMoveLeft() {
        return moveLeft;
    }

    public Image[] getMoveRight() {
        return moveRight;
    }
}
