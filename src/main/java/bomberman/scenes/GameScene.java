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
import bomberman.input.GameEventHandle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameScene {

    private static Scene scene;
    private static Group root;
    private static Canvas canvas;
    private static GraphicsContext graphicsContext;
    private static Player player = new Player(32, 160);
    private static boolean started = false;

    private static List<Entity> entities = new ArrayList<Entity>();
    private static List<Bomb> bombList = new ArrayList<Bomb>();

    private static char[][] staticMap = new char[30][30];

    private static void initScene() {
        //GamesFactory.createGame(20, 20, 26, 45, 2);

        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        canvas = new Canvas(GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);

        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Renderer.init(graphicsContext);
        addEntity(player);
        GameLoop.start(graphicsContext, entities);
        loadMap();

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

            int i = entity.getPosition().getY() / GameConstants.TILE_SIZE;
            int j = entity.getPosition().getX() / GameConstants.TILE_SIZE;
            if (entity instanceof Brick) {
                staticMap[i][j] = '*';
            } else if (entity instanceof Wall) {
                staticMap[i][j] = '#';
            } else if (entity instanceof Portal) {
                staticMap[i][j] = 'x';
            } else if (entity instanceof Bomb) {
                bombList.add((Bomb)entity);
                staticMap[i][j] = 'B';
            } else {
                staticMap[i][j] = ' ';
            }

            Collections.sort(entities, layerCompare);
        }
    }


    public static void removeStaticEntityInMap(Entity e) {
        int i = e.getPosition().getY() / GameConstants.TILE_SIZE;
        int j = e.getPosition().getX() / GameConstants.TILE_SIZE;
        staticMap[i][j] = ' ';
        if (e instanceof Bomb) {
            bombList.remove(e);
            System.out.println("Removed bomb from bombList");
        }
        System.out.println("Removed " + e.getName() + " from map");
    }

    public static void loadMap() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/map.txt"));
            String inputLine = reader.readLine();

            System.out.println("Start loading map...");

            int row = 0;
            while (inputLine != null) {
                int column = inputLine.length();

                for (int i = 0; i < column; i++) {
                    Vector2 position = new Vector2(
                            i * GameConstants.TILE_SIZE,
                            row * GameConstants.TILE_SIZE);

                    switch (inputLine.charAt(i)) {

                        case '#':
                            addEntity(new Wall(position));
                            break;

                        case '*':
                            addEntity(new Grass(position));
                            addEntity(new Brick(position));
                            break;

                        case 'x':
                            addEntity(new Portal(position));
                            break;

                        case 'U':
                            addEntity(new Grass(position));
                            addEntity(new Balloom(position));
                            break;

                        case 'O':
                            addEntity(new Grass(position));
                            addEntity(new Oneal(position));
                            break;

                        default:
                            addEntity(new Grass(position));
                    }
                }
                GameConstants.NUM_OF_COLUMNS = column;
                inputLine = reader.readLine();
                row++;
            }

            reader.close();
            GameConstants.NUM_OF_ROWS = row;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
