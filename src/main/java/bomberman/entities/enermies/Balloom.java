package bomberman.entities.enermies;

import bomberman.Renderer;
import bomberman.animation.BalloomAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;

import bomberman.entities.Vector2;

import bomberman.scenes.EasyLevel;

import java.util.Random;

public class Balloom extends Enemy {

    private Vector2 directionVector;
    private int row;

    /**
     * constructors.
     */
    public Balloom(Vector2 position) {
        this.setPosition(position);
        row = position.getY() / GameConstants.TILE_SIZE;
        init();
    }

    public Balloom(int x, int y) {
        this(new Vector2(x, y));
    }

    /**
     * @return name of the enemy.
     */
    public String getName() {
        return "Balloom";
    }

    public void init() {
        int randomNum = new Random().nextInt() % 2;
        if (randomNum == 0) {
            // init right direction
            directionVector = new Vector2(1, 0);
            setCurrentAnimation(BalloomAnimation.getMoveRight());
        } else {
            // init left direction
            directionVector = new Vector2(-1, 0);
            setCurrentAnimation(BalloomAnimation.getMoveLeft());
        }
    }

    public void update() {
        move();
    }

    private boolean checkCollision(Vector2 p) {
        int i = p.getY() / GameConstants.TILE_SIZE;
        int j = p.getX() / GameConstants.TILE_SIZE;
        if (directionVector.getX() > 0) {
            j++;
        }
        return EasyLevel.getStaticMapAt(i, j) != ' ';
    }



    public void move() {
        Vector2 newPosition = new Vector2(position).add(directionVector);
        if (!checkCollision(newPosition)) {
            this.setPosition(newPosition);
        } else {
            changeDirection();
        }
    }

    private void changeDirection() {
        directionVector = directionVector.multiple(-1);
        if (directionVector.getX() > 0) {
            currentAnimation = BalloomAnimation.getMoveRight();
        } else {
            currentAnimation = BalloomAnimation.getMoveLeft();
        }
    }

}
