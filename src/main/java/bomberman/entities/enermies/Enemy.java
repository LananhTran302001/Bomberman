package bomberman.entities.enermies;
import bomberman.Renderer;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Sprite;
import bomberman.entities.bomb.Bomb;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Date;

public abstract class Enemy extends Sprite {

    int eValue;
    Image[] currentAnimation;
    MediaPlayer deadSound = new MediaPlayer(new Media(GameSounds.ENEMY_DEAD));
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
                    GameSounds.playSound(deadSound);

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

    public abstract void move();
}
