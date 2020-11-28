package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import javafx.scene.image.Image;

public class Flame extends Entity {

    private Image[] animation;

    public Flame(Image[] flameAnimation, Vector2 position) {
        super(position);
        animation = flameAnimation;
    }

    public String getName() {
        return "fire";
    }

    public void init() {
        setLayer(GameConstants.FLAME_LAYER);
    }

    public void draw() {
        Renderer.playAnimation(animation, 5, position, size);
    }

    public void update() {

    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }
}
