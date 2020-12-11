package bomberman.entities.enermies;

import bomberman.animation.KondoriaAnimation;
import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;

public class Kondoria extends Enemy {

    private Vector2 directionVector;
    private int row;

    /**
     * constructors.
     */
    public Kondoria(Vector2 position) {
        this.setPosition(position);
        row = position.getY() / GameConstants.TILE_SIZE;
        setEValue(50);
        init();
    }

    public Kondoria(int x, int y) {
        this(new Vector2(x, y));
    }

    /**
     * @return name of the enemy.
     */
    public String getName() {
        return "Kondoria";
    }

    public void init() {
        changeDirection();
    }

    public boolean obstacleAt(Vector2 point) {
        if (point.getX() < 0 || point.getX() >= GameConstants.NUM_OF_COLUMNS || point.getY() < 0 || point.getY() >= GameConstants.NUM_OF_ROWS) {
            return true;
        }
        char c = GameScene.getStaticMapAt(point.getY(), point.getX());
        return c != ' ' && c != '*';
    }

    private void changeDirection() {
        directionVector = getRandomDirection();
        if (directionVector.getX() > 0 || directionVector.getY() > 0) {
            setCurrentAnimation(KondoriaAnimation.getMoveRight());
        } else {
            setCurrentAnimation(KondoriaAnimation.getMoveLeft());
        }
    }

    public void move() {
        if (!killed()) {
            Vector2 newPosition = Vector2.add(position, directionVector);
            if (!checkCollision(newPosition, directionVector)) {
                this.setPosition(newPosition);
            } else {
                changeDirection();
            }
        }
    }

    public void setKilledAnimation() {
        setCurrentAnimation(KondoriaAnimation.getDead());
    }
}
