package bomberman.entities.enermies.ai;

import bomberman.animation.OnealAnimation;
import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;


public class Oneal extends AIEnemy {

    /**
     * constructors.
     */
    public Oneal(Vector2 position) {
        this.setPosition(position);
        setEValue(100);
        init();
    }

    public Oneal(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Oneal";
    }

    public boolean obstacleAt(Vector2 point) {
        if (point.getX() < 0 || point.getX() >= GameConstants.NUM_OF_COLUMNS || point.getY() < 0 || point.getY() >= GameConstants.NUM_OF_ROWS) {
            return true;
        }
        return GameScene.getStaticMapAt(point.getY(), point.getX()) != ' ';
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
