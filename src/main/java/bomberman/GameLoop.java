package bomberman;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import bomberman.input.InputManager;
import bomberman.scenes.EasyLevel;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.List;

public class GameLoop {

    private static double oldGameTime;
    private static double currentGameTime;
    private static double deltaGameTime;
    private final static long startNanoTime = System.nanoTime();

    private static List<Entity> entities;

    /**
     * @return currentTime by second.
     */
    public static double getCurrentGameTime() {
        return currentGameTime;
    }

    public static double getDeltaGameTime() {
        return deltaGameTime;
    }

    public static void start(final GraphicsContext gc, List<Entity> e) {
        entities = e;

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


    public static List<Entity> getEntities() {
        return entities;
    }

    public static void update() {
        InputManager.handlePlayerInput();
        /*for (Entity entity : entities) {
            entity.update();
            if (entity instanceof Bomb) {
                if (((Bomb) entity).dead()) {
                    EasyLevel.removeEntity(entity);
                }
            }
        }

         */
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (e instanceof Bomb && ((Bomb)e).dead()) {
                iterator.remove();
                System.out.println("Removed");
                break;
            } else {
                e.update();
            }
        }
    }

    public static void render() {
        for (Entity entity : entities) {
            entity.draw();
        }
    }

}
