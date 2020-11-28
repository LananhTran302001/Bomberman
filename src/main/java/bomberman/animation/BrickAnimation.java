package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class BrickAnimation {

    private static Image[] explosionBrickAnimation = new Image[] {
            new Image(GameImages.EXPLOSION_BRICK_IMG + "00.png"),
            new Image(GameImages.EXPLOSION_BRICK_IMG + "01.png"),
            new Image(GameImages.EXPLOSION_BRICK_IMG + "02.png"),
            new Image(GameImages.EXPLOSION_BRICK_IMG + "03.png")
    };

    public static Image[] getExplosionBrickAnimation() {
        return explosionBrickAnimation;
    }
}
