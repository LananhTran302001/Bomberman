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

    @Override
    public boolean isPlayerCollideFriendly() {
        return false;
    }

    // check killed => move
    // check dead => remove

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
}
