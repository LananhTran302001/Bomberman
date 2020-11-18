package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Breakable;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Brick extends Entity implements Breakable {

    public Brick(int x, int y) {
        super(x, y);
    }

    public Brick(Vector2 position) {
        super( position);
    }

    public void init() {
        setImage(GameImages.BRICK_IMG);
        setLayer(2);
        draw();
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }
}
