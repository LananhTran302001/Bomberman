import constants.GameConstants;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop {

    private static double oldGameTime;
    private static double currentGameTime;
    private static double deltaGameTime;
    private final static long startNanoTime = System.nanoTime();

    private static GraphicsContext gc;
    private Canvas canvas;

    public static double getCurrentGameTime() {
        return currentGameTime;
    }

    public static double getDeltaGameTime() {
        return deltaGameTime;
    }

    public static void start() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                oldGameTime = currentGameTime;
                currentGameTime = (now - startNanoTime) / 1000000000.0;
                deltaGameTime = currentGameTime - oldGameTime;
                update();
                render();
            }
        };
        timer.start();
    }

    public static void update() {

    }

    public static void render() {
        gc.clearRect(0, 0, GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);
    }

}
