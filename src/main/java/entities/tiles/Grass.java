package entities.tiles;

import constants.GameConstants;
import entities.Vector2;
import javafx.scene.image.Image;


public class Grass {

    private Vector2 position;
    private Vector2 size;
    private Image image;

    public Grass() {
        position = new Vector2();
        size = new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        image = new Image(GameConstants.GRASS_IMG + "01");
    }

    public Grass(int x, int y) {
        position = new Vector2(x, y);
        size = new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        image = new Image(GameConstants.GRASS_IMG + "01");
    }
}
