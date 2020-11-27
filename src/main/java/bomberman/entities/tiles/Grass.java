package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;


public class Grass extends Entity {

    public Grass() {
        super();
        init();
    }

    public Grass(Vector2 pos) {
        super(pos);
        init();
    }

    public Grass(int x, int y) {
        super(x, y);
        init();
    }

    public String getName() {
        return "Grass";
    }

    public void init() {
        setImage(GameImages.GRASS_IMG);
        setLayer(GameConstants.GRASS_LAYER);
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }
}
