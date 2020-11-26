package bomberman.entities.bomb;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.animation.BlackBombAnimation;
import bomberman.animation.FlameAnimation;
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
        setRange(4);
    }

    public void draw() {
        if (getState() == STATE.ACTIVE) {
            Renderer.playAnimation(BlackBombAnimation.getBlackBombAnimation(), 3, position, size);
        } else if (getState() == STATE.EXPLODING) {
            explode();
        }
    }

    public void update() {

    }

}
