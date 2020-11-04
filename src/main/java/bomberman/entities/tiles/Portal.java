package bomberman.entities.tiles;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;

public class Portal extends Entity {

    public Portal(Vector2 position) {
        super(  position,
                new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE),
                GameConstants.PORTAL_IMG, 1);
    }

    public void init() {

    }

    public void draw() {

    }

    public void update() {

    }
}
