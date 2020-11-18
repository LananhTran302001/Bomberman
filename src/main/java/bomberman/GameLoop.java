package bomberman;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {

    private static double oldGameTime;
    private static double currentGameTime;
    private static double deltaGameTime;
    private final static long startNanoTime = System.nanoTime();

    private static List<Entity> entities = new ArrayList<Entity>();


    public static double getCurrentGameTime() {
        return currentGameTime;
    }

    public static double getDeltaGameTime() {
        return deltaGameTime;
    }

    public static void start(final GraphicsContext gc) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                oldGameTime = currentGameTime;
                currentGameTime = (now - startNanoTime) / 1000000000.0;
                deltaGameTime = currentGameTime - oldGameTime;

                gc.clearRect(0, 0, GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);

                update();
                render();
            }
        };
        timer.start();
    }


    public static void update() {
        for (Entity entity : entities) {
            entity.update();
        }

    }

    public static void render() {
        for (Entity entity : entities) {
            entity.draw();
        }
    }

}
