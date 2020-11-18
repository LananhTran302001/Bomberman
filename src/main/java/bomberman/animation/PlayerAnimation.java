package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class PlayerAnimation {

    Image[] moveRight = new Image[2];
    Image[] moveLeft = new Image[2];
    Image[] moveDown = new Image[2];
    Image[] moveUp = new Image[2];

    public void setUpImages() {
        moveRight[0] = new Image(GameImages.PLAYER_IMG + "R1.png");
        moveRight[1] = new Image(GameImages.PLAYER_IMG + "R2.png");

        moveLeft[0] = new Image(GameImages.PLAYER_IMG + "L1.png");
        moveLeft[1] = new Image(GameImages.PLAYER_IMG + "L2.png");

        moveDown[0] = new Image(GameImages.PLAYER_IMG + "D1.png");
        moveDown[1] = new Image(GameImages.PLAYER_IMG + "D2.png");

        moveUp[0] = new Image(GameImages.PLAYER_IMG + "U1.png");
        moveUp[1] = new Image(GameImages.PLAYER_IMG + "U2.png");
    }

    public Image[] getMoveRight() {
        return moveRight;
    }

    public Image[] getMoveLeft() {
        return moveLeft;
    }

    public Image[] getMoveDown() {
        return moveDown;
    }

    public Image[] getMoveUp() {
        return moveUp;
    }

    public Image getDie() {
        return new Image(GameImages.PLAYER_IMG + "_lose.png");
    }
}
