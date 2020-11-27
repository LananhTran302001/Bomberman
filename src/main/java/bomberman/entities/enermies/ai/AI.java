package bomberman.entities.enermies.ai;

import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.entities.enermies.Enemy;
import bomberman.entities.player.Player;
import bomberman.scenes.GameScene;

import java.util.LinkedList;

public class AI {
    Player player;
    Enemy enemy;
    GameScene gameScene;
    final Vector2[] dirVector = new Vector2[] {
            new Vector2(0, 1),
            new Vector2(0, -1),
            new Vector2(-1, 0),
            new Vector2(1, 0)
    };

    public AI(Enemy enemy, Player player, GameScene gs) {
        this.enemy = enemy;
        this.player = player;
        this.gameScene = gs;
    }

    public int findShortestDistance(Vector2 beginPoint, Vector2 endPoint) {
        if (beginPoint.equals(endPoint)) {
            return 0;
        }
        int column = GameConstants.NUM_OF_COLUMNS;
        int row = GameConstants.NUM_OF_ROWS;
        boolean[][] checked = new boolean[row][column];

        LinkedList<Distance> queue = new LinkedList<Distance>();
        queue.add(new Distance(beginPoint, 0));
        checked[beginPoint.getY()][beginPoint.getX()] = true;

        while (!queue.isEmpty()) {
            Distance currentDistance = queue.getFirst();
            queue.removeFirst();

            for (Vector2 v : dirVector) {
                int newX = currentDistance.getX() + v.getX();
                int newY = currentDistance.getY() + v.getY();

                if (checked[newY][newX]) {
                    continue;
                }

                if (newX == endPoint.getX() && newY == endPoint.getY()) {
                    return currentDistance.distance + 1;
                }

                checked[newY][newX] = true;
            }
        }
        return 1;
    }

}
