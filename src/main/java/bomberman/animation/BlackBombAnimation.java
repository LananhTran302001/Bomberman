package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class BlackBombAnimation {

    private static Image[] bombAni = new Image[] {
            new Image(GameImages.BOMB_IMG + "_0.png"),
            new Image(GameImages.BOMB_IMG + "_1.png"),
            new Image(GameImages.BOMB_IMG + "_2.png")
    };

    public static Image[] getBlackBombAnimation() {
        return bombAni;
    }
}
