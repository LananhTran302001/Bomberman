package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class PlayerAnimation {

    Image[] moveRight = new Image[2];
    Image[] moveLeft = new Image[2];
    Image[] moveDown = new Image[2];
    Image[] moveUp = new Image[2];
    Image staticDown;
    Image staticUp;
    Image staticLeft;
    Image staticRight;

    public PlayerAnimation() {
        moveRight[0] = new Image(GameImages.PLAYER_IMG + "R1.png");
        moveRight[1] = new Image(GameImages.PLAYER_IMG + "R2.png");
        staticRight = moveRight[0];

        moveLeft[0] = new Image(GameImages.PLAYER_IMG + "L1.png");
        moveLeft[1] = new Image(GameImages.PLAYER_IMG + "L2.png");
        staticLeft = moveLeft[0];

        moveDown[0] = new Image(GameImages.PLAYER_IMG + "D1.png");
        moveDown[1] = new Image(GameImages.PLAYER_IMG + "D2.png");
        staticDown = moveDown[0];

        moveUp[0] = new Image(GameImages.PLAYER_IMG + "U1.png");
        moveUp[1] = new Image(GameImages.PLAYER_IMG + "U2.png");
        staticUp = moveUp[0];
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

    public Image getStaticDown() {
        return staticDown;
    }

    public Image getStaticUp() {
        return staticUp;
    }

    public Image getStaticLeft() {
        return staticLeft;
    }

    public Image getStaticRight() {
        return staticRight;
    }
}
