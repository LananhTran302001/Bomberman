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

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public void explode() {
        if (!exploding) {
            int i = position.getY() / GameConstants.TILE_SIZE;
            int j = position.getX() / GameConstants.TILE_SIZE;

            // CENTER FLAME
            Flame centerFlame = new Flame(FlameAnimation.getCenterFlame(), position);
            centerFlame.setBound(new Rectangle2D(position.getX() - 1, position.getY() - 1, size.getX() + 2, size.getY() + 2));
            bombFlame.add(centerFlame);


            int k;


            // RIGHT FLAME

            if (!isObstacle(i, j + 1)) {
                k = 1;
                while (k < range && (j + k + 1 < GameConstants.NUM_OF_COLUMNS) && !isObstacle(i, j + k + 1)) {
                    bombFlame.add(new Flame(FlameAnimation.getHorizontalFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE)));
                    k++;
                }
                if (!isObstacle(i, j + k)) {
                    Flame rightFlame = new Flame(FlameAnimation.getRightFlame(), new Vector2((j + k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE));
                    rightFlame.setBound(new Rectangle2D(rightFlame.getPosition().getX(), rightFlame.getPosition().getY(),
                            rightFlame.getSize().getX() + 1, rightFlame.getSize().getY()));
                    bombFlame.add(rightFlame);
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
                    Flame leftFlame = new Flame(FlameAnimation.getLeftFlame(), new Vector2((j - k) * GameConstants.TILE_SIZE, i * GameConstants.TILE_SIZE));
                    leftFlame.setBound(new Rectangle2D(leftFlame.getPosition().getX() - 1, leftFlame.getPosition().getY(),
                                                        leftFlame.getSize().getX() + 2, leftFlame.getSize().getY()));
                    bombFlame.add(leftFlame);
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
                    Flame bottomFlame = new Flame(FlameAnimation.getBottomFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i + k) * GameConstants.TILE_SIZE));
                    bottomFlame.setBound(new Rectangle2D(bottomFlame.getPosition().getX(), bottomFlame.getPosition().getY(),
                            bottomFlame.getSize().getX(), bottomFlame.getSize().getY() + 1));
                    bombFlame.add(bottomFlame);
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
                    Flame topFlame = new Flame(FlameAnimation.getTopFlame(), new Vector2(j * GameConstants.TILE_SIZE, (i - k) * GameConstants.TILE_SIZE));
                    topFlame.setBound(new Rectangle2D(topFlame.getPosition().getX(), topFlame.getPosition().getY() - 1,
                            topFlame.getSize().getX(), topFlame.getSize().getY() + 2));
                    bombFlame.add(topFlame);
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


}
