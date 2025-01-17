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

    protected boolean checkCollision(Vector2 _p, Vector2 _dir) {
        Vector2 posInMap = Vector2.getPositionInMap(_p);
        if (_dir.getX() > 0) {
            posInMap.add(new Vector2(1, 0));
        }
        if (_dir.getY() > 0) {
            posInMap.add(new Vector2(0, 1));
        }
        return ai.obstacleAt(posInMap);
    }

    public void move() {
        if (!killed()) {
            if (steps <= 0) {
                if (getPosition().getX() % GameConstants.TILE_SIZE == 0
                        && getPosition().getY() % GameConstants.TILE_SIZE == 0) {
                    directionVector = ai.followPlayer();
                    if (directionVector.getPowLength() == 4) {
                        steps = NUM_OF_STEPS / 2;
                    } else {
                        steps = NUM_OF_STEPS;
                    }

                } else {
                    int roundX = Math.round(getPosition().getX() / (float)GameConstants.TILE_SIZE) * GameConstants.TILE_SIZE;
                    int roundY = Math.round(getPosition().getY() / (float)GameConstants.TILE_SIZE) * GameConstants.TILE_SIZE;
                    this.setPosition(roundX, roundY);
                }
            }

            Vector2 newPosition = Vector2.add(this.position, directionVector);
            if (!checkCollision(newPosition, directionVector)) {
                steps--;
                setAnimation(directionVector);
                this.setPosition(newPosition);
            } else {
                steps = 0;
            }
        }
    }

    public abstract void setLeftAnimation();
    public abstract void setRightAnimation();
    public abstract void setUpAnimation();
    public abstract void setDownAnimation();
}
