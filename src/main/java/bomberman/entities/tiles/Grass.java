package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import javafx.scene.image.Image;


public class Grass extends Entity {

    public Grass() {
        super();
    }

    public Grass(Vector2 pos) {
        super(pos);
    }

    public Grass(int x, int y) {
        super(x, y);
    }

    public void init() {
        setImage(GameImages.GRASS_IMG);
        setLayer(0);
        draw();
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {

    }
}
