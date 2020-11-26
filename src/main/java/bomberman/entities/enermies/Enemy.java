package bomberman.entities.enermies;
import bomberman.Renderer;
import bomberman.constants.Direction;
import bomberman.entities.Sprite;
import bomberman.entities.bomb.Bomb;
import bomberman.scenes.EasyLevel;
import javafx.scene.image.Image;

public abstract class Enemy extends Sprite {

    Image[] currentAnimation;

    public Enemy() {
        setLayer(3);
    }

    public void setCurrentAnimation(Image[] currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void draw() {
        Renderer.playAnimation(currentAnimation, 2, position, size);
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return false;
    }

    public boolean dead() {
        for (Bomb b : EasyLevel.getBombList()) {
            if (b.hitFlame(this)) {
                return true;
            }
        }

        return false;
    }

    public void move(int step, Direction direction) {
    }
}
