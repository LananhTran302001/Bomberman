package bomberman.animation;

import bomberman.constants.GameImages;
import javafx.scene.image.Image;

public class BlackBombAnimation {

    private Image[] bombAni = new Image[3];

    public BlackBombAnimation() {
        bombAni[0] = new Image(GameImages.BOMB_IMG + "_0.png");
        bombAni[1] = new Image(GameImages.BOMB_IMG + "_1.png");
        bombAni[2] = new Image(GameImages.BOMB_IMG + "_2.png");
    }

    public Image[] getBlackBombAnimation() {
        return bombAni;
    }
}
