package bomberman.entities.enermies;
import bomberman.Renderer;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Bomb;
import bomberman.gui.Sound;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Date;
import java.util.Random;

public abstract class Enemy extends Sprite {

    int eValue;
    Image[] currentAnimation;
    Sound deadSound = new Sound(GameSounds.ENEMY_DEAD);
    private Date deadTime;

    public Enemy() {
        setLayer(GameConstants.ENEMY_LAYER);
    }

    public int getEValue() {
        System.out.println("value of " + getName() + " is " + eValue);
        return eValue;
    }

    public void setEValue(int _eValue) {
        this.eValue = _eValue;
    }

    public void setCurrentAnimation(Image[] currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void draw() {
        Renderer.playAnimation(currentAnimation, 2, position, size);
    }

    public void update() {
        if (alive) {
            move();

            if (playerColliding()) {
                GameScene.getPlayer().shock();
            }
        }
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return false;
    }

    private boolean playerColliding() {
        this.setBound(new Rectangle2D(position.getX() + 5, position.getY() + 5, size.getX() - 10, size.getY() - 10));
        return collideWith(GameScene.getPlayer());
    }

    protected Vector2 getRandomDirection() {
        int[] dirX = new int[] {0, 0, -1, 1};
        int[] dirY = new int[] {-1, 1, 0, 0};
        int choice = Math.abs(new Random().nextInt() % 4);
        return new Vector2(dirX[choice], dirY[choice]);
    }


    protected boolean checkCollision(Vector2 _p, Vector2 _dir) {
        if (_p == null || _dir == null) {
            return true;
        }

        if (_dir.getX() < 0) {
            // Go Left
            return obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(1, 1))))
                    || obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(1, 31))));


        } else if (_dir.getX() > 0) {
            // Go Right
            return obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(1, 0))))
                    || obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(31, 31))));

        } else if (_dir.getY() < 0) {
            // Go Up
            return obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(31, 1))))
                    || obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(1, 1))));

        } else {
            // Go Down
            return obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(1, 31))))
                    || obstacleAt(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(31, 31))));
        }

    }

    // check killed => move
    // check dead => remove
    // called by subclass move method
    public boolean killed() {
        if (alive) {
            setBound(new Rectangle2D(position.getX() + 14, position.getY() + 14, 3, 3));
            for (Bomb b : GameScene.getBombList()) {
                if (b.hitFlame(this)) {
                    setKilledAnimation();
                    alive = !alive;
                    deadTime = new Date();
                    deadSound.play();

                    return true;
                }
            }
        }

        return false;
    }

    public boolean dead() {
        if (alive) {
            return false;
        } else {

            return new Date().getTime() - deadTime.getTime() > GameConstants.ENEMY_DEAD_TIME;
        }

    }

    @Override
    public boolean notUsed() {
        return dead();
    }

    public void move(int step, Direction direction) {
    }

    public abstract boolean obstacleAt(Vector2 point);
    public abstract void move();
}
