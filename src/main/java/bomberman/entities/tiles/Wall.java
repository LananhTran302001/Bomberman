package bomberman.entities.tiles;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Wall extends Entity {

    public Wall() {
        super(new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE), GameConstants.WALL_IMG, 1);
    }

    public Wall(int x, int y) {
        super(  new Vector2(x, y),
                new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.WALL_IMG, 1);
    }

    public Wall(Vector2 position) {
        super(  position,
                new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.WALL_IMG, 1);
    }

    public void init() {

        this.loadImage(GameConstants.WALL_IMG);
    }

    public void draw() {

    }

    public void update() {

    }
}
