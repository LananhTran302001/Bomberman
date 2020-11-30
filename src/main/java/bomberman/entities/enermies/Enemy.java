package bomberman.entities.enermies;
import bomberman.Renderer;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Sprite;
import bomberman.entities.bomb.Bomb;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Date;

public abstract class Enemy extends Sprite {

    Image[] currentAnimation;
    private Date deadTime;

    public Enemy() {
        setLayer(GameConstants.ENEMY_LAYER);
    }

    public void setCurrentAnimation(Image[] currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void draw() {
        Renderer.playAnimation(currentAnimation, 3, position, size);
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
        this.setBound(new Rectangle2D(position.getX() + 2, position.getY() + 2, size.getX() - 4, size.getY() - 4));
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
            return new Date().getTime() - deadTime.getTime() > 500;
        }

    }

    public void move(int step, Direction direction) {
    }

    public abstract void move();
}
