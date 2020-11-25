package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.animation.BlackBombAnimation;
import bomberman.entities.Vector2;

public class BlackBomb extends Bomb {

    public BlackBomb(int x, int y) {
        super(x, y);
        init();
    }

    public BlackBomb(Vector2 position) {
        super(position);
        init();
    }

    public void init() {
        setLayer(2);
    }

    public void draw() {
        Renderer.playAnimation(BlackBombAnimation.getBlackBombAnimation(), 3, position, size);
    }

    public void update() {

    }

    public void explode() {

    }
}
