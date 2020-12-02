package bomberman.scenes;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.constants.GameConstants;

import bomberman.entities.Entity;

import bomberman.entities.Vector2;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.enermies.Balloom;
import bomberman.entities.enermies.ai.Oneal;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Portal;
import bomberman.entities.tiles.Wall;
import bomberman.entities.tiles.items.*;
import bomberman.input.GameEventHandle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class GameScene {

    private static Scene scene;
    private static Group root;
    private static Canvas canvas;
    private static GraphicsContext graphicsContext;
    private static Player player;
    private static boolean started = false;

    private static List<Entity> entities = new ArrayList<Entity>();
    private static List<Bomb> bombList = new ArrayList<Bomb>();

    private static char[][] staticMap;

    private static void initScene() {
        //GamesFactory.createGame(20, 20, 26, 45, 2);
        staticMap = MapLoader.getMapFromFile("src/main/resources/data/map_02.txt");

        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        canvas = new Canvas(GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);

        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Renderer.init(graphicsContext);
        GameLoop.start(graphicsContext, entities);

        loadMap(staticMap);

        started = true;
        GameEventHandle.attachEventHandle(scene);
    }


    public static void setUpScene() {
        if (!started) {
            initScene();
            started = true;
        }
        for (Entity e : entities) {
            e.draw();
        }
    }

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

    static Comparator<Entity> layerCompare = new Comparator<Entity>() {
        public int compare(Entity o1, Entity o2) {
            return o1.getLayer() - o2.getLayer();
        }
    };

    public static void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
            System.out.println("Add entity");

            if (entity instanceof Bomb) {
                int i = entity.getPosition().getY() / GameConstants.TILE_SIZE;
                int j = entity.getPosition().getX() / GameConstants.TILE_SIZE;
                bombList.add((Bomb)entity);
                staticMap[i][j] = 'B';
            }

            Collections.sort(entities, layerCompare);
        }
    }

    public static void addLast(Entity e) {
        if (!entities.contains(e)) {
            int n = entities.size();
            entities.add(n, e);
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

        if ((e instanceof Brick) && (staticMap[i][j] == '*' )) {
            // in case there is no item under this tile
            staticMap[i][j] = ' ';

        } else if (e instanceof Bomb) {
            staticMap[i][j] = ' ';
            bombList.remove(e);
            System.out.println("Removed bomb from bombList");
        }

        System.out.println("Removed " + e.getName() + " from map");
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

                    case 'P':
                        addEntity(new Grass(position));
                        if (player == null) {
                            player = new Player(Vector2.add(position, new Vector2(0, -10)));
                            addEntity(player);
                        }
                        map[i][j] = ' ';
                        break;
                }
            }
        }
    }

    public static void gameOver() {
        player.setPosition(0, 0);
    }

    public static void setNewLevel() {

    }
}
