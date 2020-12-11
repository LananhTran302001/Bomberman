package bomberman.entities.bomb;

import bomberman.animation.FlameAnimation;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.gui.Sound;
import bomberman.scenes.GameScene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    Date startState;
    int range;
    Sound explosionSound = new Sound(GameSounds.EXPLOSION);
    Sound plantedSound = new Sound(GameSounds.PLANT_BOMB);
    private int bombWaitingTime = GameConstants.BOMB_WAITING_TIME;
    private STATE bombState = STATE.ACTIVE;
    private List<Flame> bombFlame = new ArrayList<Flame>();


    protected enum STATE {
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(Vector2 position) {
        super(position);
        setLayer(GameConstants.BOMB_LAYER);
        startState = new Date();
        plantedSound.play();
    }

    public Bomb(int x, int y) {
        this(new Vector2(x, y));
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

    public void setBombState(STATE bombState) {
        startState = new Date();
        this.bombState = bombState;
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public void explode() {
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

        plantedSound.stop();
        explosionSound.play();
    }

    protected void drawFlames() {
        for (Flame f : bombFlame) {
            f.draw();
        }
    }

    private boolean isObstacle(int i, int j) {
        char c = GameScene.getStaticMapAt(i, j);
        return c != ' ' && c != 'B';
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

        Vector2 bPos = Vector2.getPositionInMap(getPosition());
        Vector2 ePos = Vector2.getPositionInMap(realPosition);
        if (ePos.getX() == bPos.getX() || ePos.getY() == bPos.getY()) {
            for (Flame f : bombFlame) {
                Vector2 fPos = Vector2.getPositionInMap(f.getPosition());
                int disX = Math.abs(fPos.getX() - ePos.getX());
                int disY = Math.abs(fPos.getY() - ePos.getY());
                if (disX + disY <= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public STATE getState() {

        if (bombState == STATE.DEAD) {
            return bombState;
        } else if (bombState == STATE.EXPLODING) {
            if (new Date().getTime() - startState.getTime() > GameConstants.BOMB_EXPLODING_TIME) {
                setBombState(STATE.DEAD);
            }
            return bombState;

        } else {
            for (Bomb b : GameScene.getBombList()) {
                if ((b != this) && b.hitFlame(this)) {
                    this.bombWaitingTime -= 25;
                    break;
                }
            }
            if (new Date().getTime() - startState.getTime() > bombWaitingTime) {
                setBombState(STATE.EXPLODING);
                explode();
            }
            return bombState;
        }
    }

    @Override
    public boolean notUsed() {
        return getState() == STATE.DEAD;
    }
}
