package bomberman;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.enermies.Message;
import bomberman.entities.player.Player;
import bomberman.input.InputManager;
import bomberman.scenes.GameScene;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.List;

public class GameLoop {

    private static double oldGameTime;
    private static double currentGameTime;
    private static double deltaGameTime;
    private final static long startNanoTime = System.nanoTime();
    private static boolean runGame = true;
    private static boolean mute = false;


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
                if (runGame) {
                    oldGameTime = currentGameTime;
                    currentGameTime = (now - startNanoTime) / 1000000000.0;
                    deltaGameTime = currentGameTime - oldGameTime;

                    gc.clearRect(0, 0, GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);

                    update();
                    render();
                }
            }
        };
        timer.start();
    }


    public static List<Entity> getEntities() {
        return entities;
    }

    public static void update() {
        InputManager.handlePlayerInput();

        Iterator<Entity> iterator = entities.iterator();

        while (iterator.hasNext()) {
            Entity e = iterator.next();

            if (e.notUsed()) {

                if (e instanceof Enemy) {
                    Vector2 p = e.getPosition();
                    p.add(new Vector2(GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE / 2));
                    int point = ((Enemy) e).getEValue();
                    GameScene.replace(e, new Message(p, "+" + point));

                } else if (e instanceof Player){
                    GameScene.getPlayer().setPosition(0, 0);
                    iterator.remove();

                } else {
                    GameScene.removeStaticEntityInMap(e);
                    iterator.remove();
                }

                System.out.println("Removed");

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

    public static void pauseGame() {
        runGame = false;
    }

    public static void playGame() {
        runGame = true;
    }

}
