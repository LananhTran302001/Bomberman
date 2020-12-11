package bomberman;

import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.BlackBomb;
import bomberman.entities.bomb.Flame;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.enermies.Message;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Brick;
import bomberman.gui.InfoPresent;
import bomberman.gui.Sound;
import bomberman.input.GameEventHandle;
import bomberman.input.InputManager;
import bomberman.scenes.GameScene;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.List;

public class GameLoop {

    private static AnimationTimer timer;
    private static boolean started = false;
    private static double oldGameTime;
    private static double currentGameTime;
    private static double deltaGameTime;
    private final static long startNanoTime = System.nanoTime();
    private static boolean runGame = true;
    private static boolean mute = false;

    private static InfoPresent infoPresent;
    private static List<Entity> entities;
    private static Sound stageSound = new Sound(GameSounds.STAGE_1, true);

    /**
     * @return currentTime by second.
     */
    public static double getCurrentGameTime() {
        return currentGameTime;
    }

    public static double getDeltaGameTime() {
        return deltaGameTime;
    }

    public static Sound getStageSound() {
        return stageSound;
    }

    public static void start(final GraphicsContext gc, List<Entity> e) {
        if (!started) {
            entities = e;
            setStageSound(GameScene.getLevel());
            infoPresent = GameScene.getInfoPresent();
            playGame();

            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (runGame) {
                        oldGameTime = currentGameTime;
                        currentGameTime = (now - startNanoTime) / 1000000000.0;
                        deltaGameTime = currentGameTime - oldGameTime;

                        gc.clearRect(0, 0, GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);

                        render();
                        update();
                    }
                }
            };
            started = true;
            timer.start();
        } else {
            stageSound.stop();
            entities = e;
            setStageSound(GameScene.getLevel());
            stageSound.play();
        }
    }

    public static void clear() {
        entities.clear();
        stageSound.stop();
        GameEventHandle.clear();

        Brick.setPlayerCollideFriendly(false);
        Player.setCanWalkOnBrick(false);

        Flame.setKillable(true);

        BlackBomb.setFlameRange(1);

        GameConstants.STEP_LENGTH = 2;
    }

    public static void end() {
        GameEventHandle.clear();
        stageSound.stop();
    }

    private static void setStageSound(int level) {
        switch (level) {
            case 1:
                stageSound = new Sound(GameSounds.STAGE_1, true);
                break;

            case 2:
                stageSound = new Sound(GameSounds.STAGE_2, true);
                break;

            case 3:
                stageSound = new Sound(GameSounds.STAGE_3, true);
                break;

            case 4:
                stageSound = new Sound(GameSounds.STAGE_4, true);
                break;
        }
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
                    infoPresent.addPoints(point);
                    GameScene.replace(e, new Message(p, "+" + point));

                } else if (e instanceof Player){
                    GameScene.getPlayer().setPosition(0, 0);
                    iterator.remove();
                    end();
                    GameScene.gameOver();

                } else {
                    GameScene.removeStaticEntityInMap(e);
                    iterator.remove();
                }


            } else {

                e.update();
            }
        }

        infoPresent.update();
    }

    public static void render() {
        for (Entity entity : entities) {
            entity.draw();
        }
    }


    public static void pauseGame() {
        runGame = false;
        stageSound.pause();
    }

    public static void playGame() {
        runGame = true;
        stageSound.play();
    }

}
