package bomberman.scenes;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.constants.GameConstants;

import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Grass;
import bomberman.entities.tiles.Portal;
import bomberman.entities.tiles.Wall;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EasyLevel {

    static Scene scene;
    static Group root;
    static Canvas canvas;
    static GraphicsContext graphicsContext;
    static Player player;
    private static boolean started = false;

    private static List<Entity> entities = new ArrayList<Entity>();
    private static List<Grass> grassTiles = new ArrayList<Grass>();

    public static GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    private static void initScene() {
        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        canvas = new Canvas(GameConstants.CANVAS_WIDTH, GameConstants.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.AQUAMARINE);
        graphicsContext.setFill(Color.CORAL);
        Renderer.init();
        GameLoop.start(graphicsContext);
        loadMap();
    }


    public static void setUpScene() {
        if (!started) {
            initScene();
            started = true;
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Grass> getGrassTiles() {
        return grassTiles;
    }

    public static void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }


    public static void removeEntity(Entity entity) {
        entities.remove(entity);
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
                            column * GameConstants.TILE_SIZE,
                            row * GameConstants.TILE_SIZE);

                    switch (inputLine.charAt(i)) {

                        case '#':
                            addEntity(new Wall(position));
                            break;

                        case '*':
                            addEntity(new Brick(position));
                            break;

                        case 'x':
                            addEntity(new Portal(position));
                            break;

                        default:
                            grassTiles.add(new Grass(position));


                    }
                }
                inputLine = reader.readLine();
                row++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
