package bomberman;

import bomberman.animation.AnimatedImage;
import bomberman.entities.Vector2;
import bomberman.scenes.EasyLevel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {

    public static void renderImage(Image image, Vector2 position, Vector2 size) {
        GraphicsContext gc = EasyLevel.getGraphicsContext();
        gc.drawImage(image, position.getX(), position.getY(), size.getX(), size.getY());
    }

    public static void showAnimation(AnimatedImage frames, int speed, Vector2 pos, Vector2 size) {
        GraphicsContext gc = EasyLevel.getGraphicsContext();
    }

    public static void init() {
    }
}
