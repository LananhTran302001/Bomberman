package bomberman;

import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Renderer {

    private static GraphicsContext gc;

    public static void init(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    public static void renderImage(Vector2 position, Vector2 size, Image image) {
        gc.drawImage(image, position.getX(), position.getY(), size.getX(), size.getY());
    }

    public static void renderTileImage(Vector2 position, Image image) {
        renderImage(position, new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE), image);
    }

    public static void playAnimation(Image[] frames, int fps, Vector2 pos, Vector2 size) {
        int index =  ((int)(GameLoop.getCurrentGameTime() * fps) % (frames.length));
        renderImage(pos, size, frames[index]);
    }

}
