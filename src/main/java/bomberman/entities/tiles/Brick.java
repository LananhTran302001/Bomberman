package bomberman.entities.tiles;

import bomberman.constants.GameConstants;
import bomberman.entities.Breakable;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Brick extends Entity implements Breakable {

    public Brick() {
        super(  new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.BRICK_IMG, 1);
    }

    public Brick(int x, int y) {
        super(  new Vector2(x, y),
                new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.BRICK_IMG, 1);
    }

    public Brick(Vector2 position) {
        super(  position,
                new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.BRICK_IMG, 1);
    }

    public void init() {

    }

    public void draw() {

    }

    public void update() {

    }
}
