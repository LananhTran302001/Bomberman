package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Flame extends Entity {

    private static boolean killable = true;
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

    public boolean notUsed() {
        return false;
    }

    public boolean unUsed() {
        return false;
    }

    public void checkPlayerCollision() {
        if (killable) {
            if (!isPlayerCollideFriendly()) {
                this.setBound(new Rectangle2D(position.getX() + 2, position.getY() + 2, size.getX() - 4, size.getY() - 4));
                if (collideWith(GameScene.getPlayer())) {
                    GameScene.getPlayer().shock();
                }
            }
        }
    }

    public static void setKillable(boolean b) {
        killable = b;
    }
}
