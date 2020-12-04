package bomberman.scenes;

import bomberman.constants.GameConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public static char[][] getMapFromFile(String filePth) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePth));
            String inputLine = reader.readLine();

            String[] mapSize = inputLine.split(" ");

            GameConstants.NUM_OF_ROWS = Integer.parseInt(mapSize[0]);
            if (GameConstants.NUM_OF_ROWS <= 0) {
                GameConstants.NUM_OF_ROWS = 30;
            }
            GameConstants.NUM_OF_COLUMNS = Integer.parseInt(mapSize[1]);
            if (GameConstants.NUM_OF_COLUMNS <= 0) {
                GameConstants.NUM_OF_COLUMNS = 30;
            }

            GameConstants.CANVAS_WIDTH = GameConstants.NUM_OF_COLUMNS * GameConstants.TILE_SIZE;
            GameConstants.CANVAS_HEIGHT = GameConstants.NUM_OF_ROWS * GameConstants.TILE_SIZE;
            GameConstants.SCENE_WIDTH = GameConstants.CANVAS_WIDTH;
            GameConstants.SCENE_HEIGHT = GameConstants.CANVAS_HEIGHT + 2 * GameConstants.MENU_HEIGHT;

            System.out.println(GameConstants.NUM_OF_ROWS + " and " + GameConstants.NUM_OF_COLUMNS);/////////

            char[][] map = new char[GameConstants.NUM_OF_ROWS][GameConstants.NUM_OF_COLUMNS];

            inputLine = reader.readLine();
            int row = 0;
            while (inputLine != null) {
                int column = inputLine.length();

                for (int i = 0; i < column; i++) {
                    map[row][i] = inputLine.charAt(i);
                }

                inputLine = reader.readLine();
                row++;
            }

            reader.close();
            return map;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
