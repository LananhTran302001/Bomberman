package bomberman.entities.tiles;

import bomberman.Renderer;
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

    public void init() {
        setImage(GameImages.GRASS_IMG);
        setLayer(0);
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }
}
