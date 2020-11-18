package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Wall extends Entity {

    public Wall() {
        super();
    }

    public Wall(Vector2 position) {
        super(position);
    }

    public void init() {
        setImage(GameImages.WALL_IMG);
        setLayer(2);
        draw();
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }
}
