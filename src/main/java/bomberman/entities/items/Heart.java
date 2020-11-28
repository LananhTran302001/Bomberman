package bomberman.entities.items;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Heart extends Entity {
    public Heart() {
        super();
        init();
    }

    public Heart(Vector2 position) {
        super(position);
        init();
    }

    public String getName() {
        return "Item heart";
    }

    public void init() {
        setImage(GameImages.HEART_IMG);
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
}
