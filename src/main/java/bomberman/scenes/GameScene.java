package bomberman.scenes;

import bomberman.GameLoop;
import bomberman.Renderer;

import bomberman.constants.GameConstants;

import bomberman.constants.GameSounds;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.BlackBomb;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.enermies.Balloom;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.enermies.Kondoria;
import bomberman.entities.enermies.ai.Oneal;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Portal;
import bomberman.entities.tiles.Wall;
import bomberman.entities.tiles.items.*;

import bomberman.gui.Menu;
import bomberman.gui.Sound;
import bomberman.input.GameEventHandle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

import java.util.*;

public class GameScene {

    private static Scene scene;
    private static Group root;
    private static BorderPane gameScreen;
    private static Canvas canvas;
    private static GraphicsContext graphicsContext;

    private static Player player = new Player(new Vector2(0, 0));
    private static boolean started = false;
    private static int level = 0;

    private static List<Entity> entities = new ArrayList<Entity>();
    private static List<Bomb> bombList = new ArrayList<Bomb>();
    private static char[][] staticMap;

    private static final Sound gameOverSound = new Sound(GameSounds.GAME_OVER);

    private static void initScene(int _level) {
        staticMap = MapLoader.getMapFromFile("src/main/resources/data/map" + level + ".txt");

        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        canvas = new Canvas(GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);
        gameScreen = new BorderPane();
        gameScreen.setTop(new Menu().getMenuBar());
        gameScreen.setCenter(canvas);

        root.getChildren().add(gameScreen);
        graphicsContext = canvas.getGraphicsContext2D();
        Renderer.init(graphicsContext);

        loadMap(staticMap);
        GameLoop.start(graphicsContext, entities);
        started = true;
        GameEventHandle.attachEventHandle(scene);
    }

    private static void clear() {
        entities.clear();
        bombList.clear();
        staticMap = null;
        gameOverSound.stop();
        GameLoop.clear();
    }

    private static void setUpScene(int _level) {
        if (!started) {
            level = _level;
            initScene(_level);
            return;
        }
        clear();
        level = _level;
        staticMap = MapLoader.getMapFromFile("src/main/resources/data/map" + level + ".txt");
        loadMap(staticMap);
        GameLoop.start(graphicsContext, entities);
    }



    public static void replayGame() {
        setUpScene(1);
    }

    public static void setNewLevel() {
        if (level < 5) {
            setUpScene(level + 1);
        }
    }


    /**
     * SETTERS AND GETTERS.
     */
    public static GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Player getPlayer() {
        return player;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Bomb> getBombList() {
        return bombList;
    }

    public static char[][] getStaticMap() {
        return staticMap;
    }

    public static char getStaticMapAt(int row, int column) {
        return staticMap[row][column];
    }

    public static int getLevel() {
        return level;
    }

    /**
     * Compare to set layer.
     */
    static Comparator<Entity> layerCompare = new Comparator<Entity>() {
        public int compare(Entity o1, Entity o2) {
            return o1.getLayer() - o2.getLayer();
        }
    };

    /**
     * add entity to game.
     */
    public static void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);

            if (entity instanceof Bomb) {
                int i = entity.getPosition().getY() / GameConstants.TILE_SIZE;
                int j = entity.getPosition().getX() / GameConstants.TILE_SIZE;
                bombList.add((Bomb)entity);
                staticMap[i][j] = 'B';
            }

            Collections.sort(entities, layerCompare);
        }
    }

    public static int getIndex(Entity e) {
        return entities.indexOf(e);
    }

    public static void replace(Entity oldEntity, Entity newEntity) {
        int index = entities.indexOf(oldEntity);
        if (index >= 0 && index < entities.size()) {
            entities.set(index, newEntity);
        }
    }

    public static void removeStaticEntityInMap(Entity e) {
        if (e == null) {
            return;
        }
        int i = e.getPosition().getY() / GameConstants.TILE_SIZE;
        int j = e.getPosition().getX() / GameConstants.TILE_SIZE;

        if ((e instanceof Brick) && (staticMap[i][j] == '*')) {
            // in case there is no item under this tile
            staticMap[i][j] = ' ';

        } else if (e instanceof Bomb) {
            staticMap[i][j] = ' ';
            bombList.remove(e);
        }

    }

    public static boolean killedAllEnemies() {
        for (Entity e : entities) {
            if (e instanceof Enemy) {
                return false;
            }
        }
        return true;
    }

    public static void gameOver() {
        player.stop();
        gameOverSound.play();
    }



    public static void loadMap(char[][] map) {
        if (map == null) {
            System.out.println("Map game is empty");
            return;
        }

        int row = GameConstants.NUM_OF_ROWS;
        int column = GameConstants.NUM_OF_COLUMNS;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Vector2 position = new Vector2(j * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE);

                switch (map[i][j]) {
                    case ' ':
                        addEntity(new Grass(position));
                        break;

                    case '#':
                        addEntity(new Wall(position));
                        break;

                    case '*':
                        addEntity(new Grass(position));
                        addEntity(new Brick(position));
                        break;

                    case 'U':
                        addEntity(new Grass(position));
                        addEntity(new Balloom(position));
                        map[i][j] = ' ';
                        break;

                    case 'O':
                        addEntity(new Grass(position));
                        addEntity(new Oneal(position));
                        map[i][j] = ' ';
                        break;

                    case 'K' :
                        addEntity(new Grass(position));
                        addEntity(new Kondoria(position));
                        map[i][j] = ' ';
                        break;

                    case 'x':
                        addEntity(new Portal(position));
                        break;

                    case '1':
                        addEntity(new Grass(position));
                        addEntity(new FireUp(position));
                        addEntity(new Brick(position));
                        map[i][j] = '*';
                        break;

                    case '2':
                        addEntity(new Grass(position));
                        addEntity(new BlockPass(position));
                        addEntity(new Brick(position));
                        map[i][j] = '*';
                        break;

                    case '3':
                        addEntity(new Grass(position));
                        addEntity(new Heart(position));
                        addEntity(new Brick(position));
                        map[i][j] = '*';
                        break;

                    case '4':
                        addEntity(new Grass(position));
                        addEntity(new SpeedUp(position));
                        map[i][j] = ' ';
                        break;

                    case '5':
                        addEntity(new Grass(position));
                        addEntity(new FireSuit(position));
                        map[i][j] = ' ';
                        break;

                    case 'P':
                        addEntity(new Grass(position));

                        player.setPosition(position);
                        if (!entities.contains(player))  {
                            addEntity(player);
                            player.init();
                        }

                        player.stop();
                        map[i][j] = ' ';
                        break;
                }
            }
        }
    }

}
