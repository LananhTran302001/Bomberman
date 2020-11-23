package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class PlayerAnimation {

    Image[] moveRight = new Image[3];
    Image[] moveLeft = new Image[3];
    Image[] moveDown = new Image[2];
    Image[] moveUp = new Image[2];

    public PlayerAnimation() {
        moveRight[0] = new Image(GameImages.PLAYER_IMG + "_right_01.png");
        moveRight[1] = new Image(GameImages.PLAYER_IMG + "_right_02.png");
        moveRight[2] = new Image(GameImages.PLAYER_IMG + "_right_03.png");

        moveLeft[0] = new Image(GameImages.PLAYER_IMG + "_left_01.png");
        moveLeft[1] = new Image(GameImages.PLAYER_IMG + "_left_02.png");
        moveLeft[2] = new Image(GameImages.PLAYER_IMG + "_left_03.png");

        moveDown[0] = new Image(GameImages.PLAYER_IMG + "_down_01.png");
        moveDown[1] = new Image(GameImages.PLAYER_IMG + "_down_02.png");

        moveUp[0] = new Image(GameImages.PLAYER_IMG + "_up_01.png");
        moveUp[1] = new Image(GameImages.PLAYER_IMG + "_up_02.png");
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
        return new Image(GameImages.PLAYER_IMG + "_died.png");
    }

    public Image getStaticDown() {
        return new Image(GameImages.PLAYER_IMG + "_down.png");
    }

    public Image getStaticUp() {
        return new Image(GameImages.PLAYER_IMG + "_up.png");
    }

    public Image getStaticLeft() {
        return new Image(GameImages.PLAYER_IMG + "_left.png");
    }

    public Image getStaticRight() {
        return new Image(GameImages.PLAYER_IMG + "_right.png");
    }
}
