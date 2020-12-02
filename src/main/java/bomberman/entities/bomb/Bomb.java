package bomberman.entities.bomb;

import bomberman.animation.FlameAnimation;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    Date startDate;
    int range;
    private boolean exploding = false;
    private List<Flame> bombFlame = new ArrayList<Flame>();

    protected enum STATE {
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(Vector2 position) {
        super(position);
        setLayer(GameConstants.BOMB_LAYER);
        startDate = new Date();
    }

    public Bomb(int x, int y) {
        super(x, y);
        setLayer(GameConstants.BOMB_LAYER);
        startDate = new Date();
    }

    public String getName() {
        return "Bomb";
    }

    public void setRange(int a) {
        range = a;
    }

    public int getRange() {
        return range;
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public void explode() {
        if (!exploding) {
            int i = position.getY() / GameConstants.TILE_SIZE;
            int j = position.getX() / GameConstants.TILE_SIZE;

            // CENTER FLAME
            bombFlame.add(new Flame(FlameAnimation.getCenterFlame(), position));


            int k;


            // RIGHT FLAME

            if (!isObstacle(i, j + 1)) {
                k = 1;
                while (k < range && (j + k + 1 < GameConstants.NUM_OF_COLUMNS) && !isObstacle(i, j + k + 1)) {
                    bombFlame.add(new Flame(FlameAnimation.getHorizontalFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
                    k++;
                }
                if (!isObstacle(i, j + k)) {
                    bombFlame.add(new Flame(FlameAnimation.getRightFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
                }
            }

            // LEFT FLAME

            if (!isObstacle(i, j - 1)) {
                k = 1;
                while (k < range && (j - k > 1) && !isObstacle(i, j - k - 1)) {
                    bombFlame.add(new Flame(FlameAnimation.getHorizontalFlame(), new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
                    k++;
                }
                if (!isObstacle(i, j - k)) {
                    bombFlame.add(new Flame(FlameAnimation.getLeftFlame(), new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
                }
            }


            // BOTTOM FLAME

            if (!isObstacle(i + 1, j)) {
                k = 1;
                while (k < range && (i + k + 1 < GameConstants.NUM_OF_ROWS) && !isObstacle(i + k + 1, j)) {
                    bombFlame.add(new Flame(FlameAnimation.getVerticalFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE)));
                    k++;
                }
                if (!isObstacle(i + k, j)) {
                    bombFlame.add(new Flame(FlameAnimation.getBottomFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE)));
                }
            }


            // TOP FLAME

            if (!isObstacle(i - 1, j)) {
                k = 1;
                while (k < range && (i - k > 1) && !isObstacle(i - k - 1, j)) {
                    bombFlame.add(new Flame(FlameAnimation.getVerticalFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE)));
                    k++;
                }
                if (!isObstacle(i - k, j)) {
                    bombFlame.add(new Flame(FlameAnimation.getTopFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE)));
                }
            }

            exploding = true;

        } else {
            for (Flame f : bombFlame) {
                f.draw();
            }
        }

    }

    private boolean isObstacle(int i, int j) {
        char c = GameScene.getStaticMapAt(i, j);
        return c != ' ';
    }

    public boolean hitFlame(Entity e) {
        for (Flame f : bombFlame) {
            if (f.overlap(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean tileInBombRange(Vector2 realPosition) {
        if (exploding) {
            Vector2 bPos = Vector2.getPositionInMap(getPosition());
            Vector2 ePos = Vector2.getPositionInMap(realPosition);
            if (ePos.getX() == bPos.getX() || ePos.getY() == bPos.getY()) {
                return Math.abs(ePos.getX() - bPos.getX()) + Math.abs(ePos.getY() - bPos.getY()) <= range;
            }
        }
        return false;
    }

    public STATE getState() {
        long lifeTime = new Date().getTime() - startDate.getTime();
        if (lifeTime > GameConstants.BOMB_EXPLODING_TIME + GameConstants.BOMB_WAITING_TIME) {
            return STATE.DEAD;
        } else if (lifeTime > GameConstants.BOMB_WAITING_TIME) {
            return STATE.EXPLODING;
        } else {
            return STATE.ACTIVE;
        }
    }

    public boolean dead() {
        return getState() == STATE.DEAD;
    }

    @Override
    public boolean notUsed() {
        return dead();
    }
}
