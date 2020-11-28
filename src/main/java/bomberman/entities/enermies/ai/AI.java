package bomberman.entities.enermies.ai;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.player.Player;
import bomberman.scenes.GameScene;

import java.util.LinkedList;

public class AI {

    Player player;
    Enemy enemy;
    static final Vector2[] dirVector = new Vector2[] {
            new Vector2(0, 1),
            new Vector2(0, -1),
            new Vector2(-1, 0),
            new Vector2(1, 0)
    };

    static final int INF = 10000000;

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

        LinkedList<Distance> queue = new LinkedList<Distance>();
        queue.add(new Distance(beginPoint, 0));
        checked[beginPoint.getX()][beginPoint.getY()] = true;

        while (!queue.isEmpty()) {
            Distance currentDistance = queue.getFirst();
            queue.removeFirst();

            for (Vector2 v : dirVector) {
                Vector2 nextPoint = Vector2.add(currentDistance.point, v);

                if (obstacleAt(nextPoint) || checked[nextPoint.getX()][nextPoint.getY()]) {
                    continue;
                }

                if (nextPoint.equals(endPoint)) {
                    return currentDistance.distance + 1;
                }

                checked[nextPoint.getX()][nextPoint.getY()] = true;
                queue.add(new Distance(nextPoint, currentDistance.distance + 1));
            }
        }
        return INF;
    }

    public boolean obstacleAt(Vector2 point) {
        if (point.getX() < 0 || point.getX() >= GameConstants.NUM_OF_COLUMNS || point.getY() < 0 || point.getY() >= GameConstants.NUM_OF_ROWS) {
            return true;
        }
        if (GameScene.getStaticMapAt(point.getY(), point.getX()) != ' ') {
            return true;
        }
        return false;
    }

    public Vector2 findShortestPath(Vector2 destination) {
        int shortestPath = INF;
        Vector2 dir = new Vector2(0, 0);

        for (Vector2 v : dirVector) {
            Vector2 nextPoint = Vector2.add(getPositionInMap(enemy), v);
            int temp = findShortestDistance(nextPoint, destination);

            if (shortestPath > temp) {
                shortestPath = temp;
                dir = v;
            }
        }

        return dir;
    }

    public Vector2 followPlayer() {
        return findShortestPath(getPositionInMap(player));
    }

    public Vector2 getPositionInMap(Entity e) {
        int x = e.getPosition().getX() + e.getSize().getX() - GameConstants.TILE_SIZE;
        int y = e.getPosition().getY() + e.getSize().getY() - GameConstants.TILE_SIZE;
        x = Math.round(x / (float)GameConstants.TILE_SIZE);
        y = Math.round(y / (float)GameConstants.TILE_SIZE);

        return new Vector2(x, y);
    }
}
