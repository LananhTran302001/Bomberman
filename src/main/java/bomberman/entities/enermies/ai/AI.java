package bomberman.entities.enermies.ai;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.player.Player;
import bomberman.scenes.GameScene;

import java.util.LinkedList;
import java.util.Random;

public class AI {

    Player player;
    Enemy enemy;
    final int viewRadius = GameConstants.AI_VIEW_RADIUS;
    static final int INF = 10000000;
    final int[] shiftX = new int[] {0, 0, -1, 1};
    final int[] shiftY = new int[] {1, -1, 0, 0};

    public AI(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    public int findShortestDistance(Vector2 beginPoint, Vector2 endPoint) {
        if (beginPoint.equals(endPoint)) {
            return 0;
        }
        int column = GameConstants.NUM_OF_COLUMNS;
        int row = GameConstants.NUM_OF_ROWS;
        boolean[][] checked = new boolean[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                checked[i][j] = false;
            }
        }

        LinkedList<Distance> queue = new LinkedList<Distance>();
        queue.add(new Distance(beginPoint, 0));
        checked[beginPoint.getX()][beginPoint.getY()] = true;

        while (!queue.isEmpty()) {
            Distance currentDistance = queue.getFirst();
            queue.removeFirst();

            for (int i = 0; i < 4; i++) {
                Vector2 nextPoint = Vector2.add(currentDistance.point, new Vector2(shiftX[i], shiftY[i]));
                if (obstacleAt(nextPoint) || checked[nextPoint.getX()][nextPoint.getY()]) {
                    continue;
                }

                if (nextPoint.equals(endPoint)) {
                    return currentDistance.distance + 1;
                }

                checked[nextPoint.getX()][nextPoint.getY()] = true;
                if (currentDistance.distance < viewRadius) {
                    queue.add(new Distance(nextPoint, currentDistance.distance + 1));
                }
            }
        }
        return INF;
    }

    public boolean obstacleAt(Vector2 point) {
        if (point.getX() < 0 || point.getX() >= GameConstants.NUM_OF_COLUMNS || point.getY() < 0 || point.getY() >= GameConstants.NUM_OF_ROWS) {
            return true;
        }
        return GameScene.getStaticMapAt(point.getY(), point.getX()) != ' ';
    }

    public Vector2 findShortestPath(Vector2 destination) {
        int shortestPath = INF;
        Vector2 dir = getRandomDirection();

        for (int i = 0; i < 4; i++) {
            Vector2 nextPoint = Vector2.add(Vector2.getPositionInMap(enemy.getPosition()), new Vector2(shiftX[i], shiftY[i]));

            if (obstacleAt(nextPoint)) {
                continue;
            }

            int temp = findShortestDistance(nextPoint, destination);

            if (shortestPath > temp) {
                shortestPath = temp;
                dir = new Vector2(shiftX[i], shiftY[i]);
            }
        }
        return dir;
    }

    public Vector2 followPlayer() {
        return findShortestPath(Vector2.getPositionInMap(player.getPosition(), player.getSize()));
    }

    public Vector2 getRandomDirection() {
        int i = Math.abs(new Random().nextInt()) % 4;
        return new Vector2(shiftX[i], shiftY[i]);
    }
}
