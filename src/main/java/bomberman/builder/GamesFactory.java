package bomberman.builder;

import bomberman.constants.GameConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GamesFactory {

    private static void writeToFile(String pathFile, String line) {
        try {
            FileWriter writer = new FileWriter(pathFile);
            writer.write(line);
            writer.close();

        } catch (IOException e) {
            System.out.println("Error when open file to write -> can not init game");
            e.printStackTrace();
        }
    }


    public static void createGame(int row, int column, int wall, int brick, int portal) {

        GameConstants.CANVAS_HEIGHT = row * GameConstants.TILE_SIZE;
        GameConstants.CANVAS_WIDTH = column * GameConstants.TILE_SIZE;

        // Init by a 2d array
        char[][] array = new char[row][column];

        for (int i = 0; i < row; i++) {
            array[i][0] = '#';
            array[i][column - 1] = '#';
        }
        for (int i = 0; i < column; i++) {
            array[0][i] = '#';
            array[row - 1][i] = '#';
        }

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                array[i][j] = ' ';
            }
        }


        // PLANT
        Random random = new Random();

        // plant wall
        while (wall > 0) {
            int i = 1 + random.nextInt(row - 2);
            int j = 1 + random.nextInt(column - 2);
            if (array[i][j] == ' ') {
                array[i][j] = '#';
                wall--;
            }
        }

        // plant brick
        while (brick > 0) {
            int i = 1 + random.nextInt(row - 2);
            int j = 1 + random.nextInt(column - 2);
            if (array[i][j] == ' ') {
                array[i][j] = '*';
                brick--;
            }
        }

        // plant portal
        while (portal > 0) {
            int i = 1 + random.nextInt(row - 2);
            int j = 1 + random.nextInt(column - 2);
            if (array[i][j] == ' ') {
                array[i][j] = 'x';
                portal--;
            }
        }



        // Convert array to string
        String bound = "";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                bound += array[i][j];
            }
            bound += '\n';
        }



        // Write string to file, then you can edit that file or whatever
        writeToFile("src/main/resources/data/map.txt", bound);

    }



    public void createGame(int row, int column) {
        createGame(row, column, 0, 0, 0);
    }

}
