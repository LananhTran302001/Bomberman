package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Flame extends Entity {

    private Image[] animation;

    public Flame(Image[] flameAnimation, Vector2 position) {
        super(position);
        animation = flameAnimation;
        init();
    }

    public String getName() {
        return "fire";
    }

    public void init() {
        setLayer(GameConstants.FLAME_LAYER);
        setBound(new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY()));
    }

    public void draw() {
        Renderer.playAnimation(animation, 5, position, size);
        checkPlayerCollision();
    }

    public void update() {}

    public boolean isPlayerCollideFriendly() {
        return false;
    }

    public void checkPlayerCollision() {
        if (!isPlayerCollideFriendly()) {
            this.setBound(new Rectangle2D(position.getX() + 10, position.getY() + 10, size.getX() - 20, size.getY() - 20));
            if (collideWith(GameScene.getPlayer())) {
                GameScene.getPlayer().shock();
            }
        }
    }
}
