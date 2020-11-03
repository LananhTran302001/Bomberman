package entities.tiles;

import constants.GameConstants;
import entities.Entity;
import entities.Vector2;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall() {
        super(50, 50, GameConstants.WALL_IMG, 1);
    }

    public Wall(int x, int y) {
        super(x, y, 50, 50, GameConstants.WALL_IMG, 1);
    }


    public void init() {

        this.loadImage(GameConstants.WALL_IMG);
    }
}
