package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Portal extends Entity {

    public Portal() {
        super();
        init();
    }

    public Portal(Vector2 position) {
        super(position);
        init();
    }

    public String getName() {
        return "Portal";
    }

    public void init() {
        setImage(GameImages.PORTAL_IMG);
        setLayer(GameConstants.OBSTACLE_LAYER);
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }

    public boolean isPlayerCollideFriendly() {
        return false;
    }

    public boolean notUsed() {
        return false;
    }
}
