package bomberman.entities.enermies.ai;

import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.entities.enermies.Enemy;
import bomberman.scenes.GameScene;

public abstract class AIEnemy extends Enemy {

    private Vector2 directionVector;
    private AI ai;
    final int NUM_OF_STEPS = 32;
    int steps = NUM_OF_STEPS;

    public void init() {
        ai = new AI(this, GameScene.getPlayer());
        directionVector = new Vector2(1, 0);
        setRightAnimation();
    }

    public void update() {
        if (alive) {
            move();
        }
    }

    private boolean checkCollision(Vector2 p) {
        int i = p.getY() / GameConstants.TILE_SIZE;
        int j = p.getX() / GameConstants.TILE_SIZE;
        if (directionVector.getX() > 0) {
            j++;
        }
        if (directionVector.getY() > 0) {
            i++;
        }
        return GameScene.getStaticMapAt(i, j) != ' ';
    }

    private void setAnimation(Vector2 directionVector) {
        if (directionVector.getY() < 0) {
            setUpAnimation();
        } else if (directionVector.getX() < 0) {
            setLeftAnimation();
        } else if (directionVector.getX() >= 0) {
            setRightAnimation();
        } else {
            setDownAnimation();
        }
    }

    public void move() {
        if (!killed()) {
            if (steps <= 0) {
                directionVector = ai.followPlayer();
                steps = NUM_OF_STEPS;
            }

            Vector2 newPosition = new Vector2(position).add(directionVector);
            if (!checkCollision(newPosition)) {
                steps--;
                setAnimation(directionVector);
                this.setPosition(newPosition);
            } else {
                steps = 0;
                //directionVector = ai.followPlayer();
            }
        }
    }

    public abstract void setLeftAnimation();
    public abstract void setRightAnimation();
    public abstract void setUpAnimation();
    public abstract void setDownAnimation();
}
