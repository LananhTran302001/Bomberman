package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.animation.BlackBombAnimation;
import bomberman.entities.Vector2;

public class BlackBomb extends Bomb {

    private static int flameRange = 1;

    public BlackBomb(int x, int y) {
        super(x, y);
        init();
    }

    public BlackBomb(Vector2 position) {
        super(position);
        init();
    }

    public static void setFlameRange(int _flameRange) {
        if (_flameRange > 0 && _flameRange < 6) {
            flameRange = _flameRange;
        } else {
            flameRange = 1;
        }
    }

    public static int getFlameRange() {
        return flameRange;
    }

    public void init() {
        setRange(flameRange);
    }

    public void draw() {
        if (getState() == STATE.ACTIVE) {
            Renderer.playAnimation(BlackBombAnimation.getBlackBombAnimation(), 3, position, size);
        } else if (getState() == STATE.EXPLODING) {
            drawFlames();
        }
    }

    public void update() {

    }

}
