package bomberman.entities.enermies.ai;

import bomberman.animation.MinvoAnimation;
import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Bomb;
import bomberman.scenes.GameScene;

public class Minvo extends AIEnemy {


    public String getName() {
        return "Minvo";
    }

    public void setLeftAnimation() {
        setCurrentAnimation(MinvoAnimation.getMoveLeft());
    }

    public void setRightAnimation() {
        setCurrentAnimation(MinvoAnimation.getMoveRight());
    }

    public void setUpAnimation() {
        setCurrentAnimation(MinvoAnimation.getMoveLeft());
    }

    public void setDownAnimation() {
        setCurrentAnimation(MinvoAnimation.getMoveRight());
    }

    public boolean inBombRange() {
        for (Bomb b : GameScene.getBombList()) {
            if (b.tileInBombRange(this.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public boolean obstacleAt(Vector2 point) {
        if (point.getX() < 0 || point.getX() >= GameConstants.NUM_OF_COLUMNS || point.getY() < 0 || point.getY() >= GameConstants.NUM_OF_ROWS) {
            return true;
        }
        if (inBombRange()) {
            return true;
        }
        return GameScene.getStaticMapAt(point.getY(), point.getX()) != ' ';
    }


    public void setKilledAnimation() {
        setCurrentAnimation(MinvoAnimation.getDead());
    }
}
