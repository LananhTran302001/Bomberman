package bomberman.entities.enermies.ai;

import bomberman.animation.OnealAnimation;
import bomberman.entities.Vector2;


public class Oneal extends AIEnemy {

    /**
     * constructors.
     */
    public Oneal(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Oneal(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Oneal";
    }

    public void setKilledAnimation() {
        setCurrentAnimation(OnealAnimation.getDead());
    }

    public void setLeftAnimation() {
        setCurrentAnimation(OnealAnimation.getMoveLeft());
    }

    public void setRightAnimation() {
        setCurrentAnimation(OnealAnimation.getMoveRight());
    }

    public void setUpAnimation() {
        setRightAnimation();
    }

    public void setDownAnimation() {
        setLeftAnimation();
    }
}
