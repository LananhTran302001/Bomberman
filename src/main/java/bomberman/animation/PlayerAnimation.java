package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class PlayerAnimation {

    Image[] moveRight = new Image[4];
    Image[] moveLeft = new Image[4];
    Image[] moveDown = new Image[4];
    Image[] moveUp = new Image[4];

    public PlayerAnimation() {
        moveRight[0] = new Image(GameImages.PLAYER_IMG + "_right.png");
        moveRight[1] = new Image(GameImages.PLAYER_IMG + "_right_01.png");
        moveRight[2] = new Image(GameImages.PLAYER_IMG + "_right_02.png");
        moveRight[3] = new Image(GameImages.PLAYER_IMG + "_right_03.png");

        moveLeft[0] = new Image(GameImages.PLAYER_IMG + "_left.png");
        moveLeft[1] = new Image(GameImages.PLAYER_IMG + "_left_01.png");
        moveLeft[2] = new Image(GameImages.PLAYER_IMG + "_left_02.png");
        moveLeft[3] = new Image(GameImages.PLAYER_IMG + "_left_03.png");

        moveDown[0] = new Image(GameImages.PLAYER_IMG + "_down.png");
        moveDown[1] = new Image(GameImages.PLAYER_IMG + "_down_01.png");
        moveDown[2] = new Image(GameImages.PLAYER_IMG + "_down_02.png");
        moveDown[3] = new Image(GameImages.PLAYER_IMG + "_down_03.png");

        moveUp[0] = new Image(GameImages.PLAYER_IMG + "_up.png");
        moveUp[1] = new Image(GameImages.PLAYER_IMG + "_up_01.png");
        moveUp[2] = new Image(GameImages.PLAYER_IMG + "_up_02.png");
        moveUp[3] = new Image(GameImages.PLAYER_IMG + "_up_03.png");
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
        return moveDown[0];
    }

    public Image getStaticUp() {
        return moveUp[0];
    }

    public Image getStaticLeft() {
        return moveLeft[0];
    }

    public Image getStaticRight() {
        return moveRight[0];
    }
}
