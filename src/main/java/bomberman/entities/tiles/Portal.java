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

    public void init() {
        setImage(GameImages.PORTAL_IMG);
        setLayer(1);
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }
}
