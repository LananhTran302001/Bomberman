package bomberman.entities.player;

import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;

import javafx.scene.image.Image;

import java.util.Date;


public class Player extends Sprite {

    private int lives = 3;

    private Date lastHitTime;
    private Date deadTime;

    private Image[] currentFrames = new Image[] {
            PlayerAnimation.getMoveDown()[0],
    };

    private static boolean canWalkOnBrick = false;

    public Player(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Player(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Player";
    }

    private void setCurrentFrames(Image[] images) {
        int n = images.length;
        this.currentFrames = new Image[n];
        currentFrames = images;
    }

    private void setCurrentFrames(Image image) {
        currentFrames = new Image[1];
        currentFrames[0] = image;
    }

    public void init() {
        lives = 3;
        alive = true;
        this.setSize(30, 38);
        setLayer(GameConstants.PLAYER_LAYER);
    }


    public void draw() {
        if (currentFrames.length > 1) {
            Renderer.playAnimation(currentFrames, 4, position, size);
        } else {
            Renderer.renderImage(position, size, currentFrames[0]);
        }
    }

    public void update() {
    }

    private void die() {
        lives = 0;
        alive = false;
        deadTime = new Date();
        setDeadAnimation();
        this.setPosition(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        draw();
    }

    public boolean lagging() {
        if (lastHitTime == null) {
            return false;
        }
        return new Date().getTime() - lastHitTime.getTime() < GameConstants.PLAYER_LAG_TIME;
    }

    public void shock() {
        if (!lagging()) {

            if (lives > 1) {
                lives--;
                setKilledAnimation();
                lastHitTime = new Date();
                this.setPosition(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
                draw();

            } else {
                die();
            }

        }
    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public static void setCanWalkOnBrick(boolean _canWalkOnBrick) {
        canWalkOnBrick = _canWalkOnBrick;
    }

    private boolean barrierTile(Vector2 posInMap) {
        if (posInMap == null) {
            return true;
        }
        char c = GameScene.getStaticMapAt(posInMap.getY(), posInMap.getX());
        if (canWalkOnBrick && c == '*') {
            return false;
        }
        return c != ' ' && c != 'B';

    }

    public boolean checkCollision(Vector2 _p, Vector2 _dir) {

        if (_p == null || _dir == null) {
            return true;
        }

        if (_dir.getX() < 0) {
            // Go Left
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 35))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 20))));


        } else if (_dir.getX() > 0) {
            // Go Right
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(28, 20))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(28, 35))));

        } else if (_dir.getY() < 0) {
            // Go Up
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(20, 10))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 10))));

        } else {
            // Go Down
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 35))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(20, 35))));
        }

    }

    public void move(int _step, Direction _direction) {
        Vector2 directionVector = new Vector2();

        switch (_direction) {
            case UP:
                directionVector = new Vector2(0, -1);
                currentFrames = PlayerAnimation.getMoveUp();
                break;

            case DOWN:
                directionVector = new Vector2(0, 1);
                currentFrames = PlayerAnimation.getMoveDown();
                break;

            case LEFT:
                directionVector = new Vector2(-1, 0);
                currentFrames = PlayerAnimation.getMoveLeft();
                break;

            case RIGHT:
                directionVector = new Vector2(1, 0);
                currentFrames = PlayerAnimation.getMoveRight();
                break;
        }

        Vector2 newPosition = Vector2.add(position, Vector2.multiple(directionVector,_step * GameConstants.STEP_LENGTH));

        if (!checkCollision(newPosition, directionVector)) {
            this.setPosition(newPosition);
        }

    }

    public void stopAnimation() {
        setCurrentFrames(currentFrames[0]);
    }

    public void setKilledAnimation() {
        setCurrentFrames(PlayerAnimation.getShock());
    }

    public void setDeadAnimation() {
        setCurrentFrames(PlayerAnimation.getDead());
    }

    // check killed => move
    // check dead => remove
    public boolean killed() {
        alive = lives > 0;
        return !alive;
    }

    public boolean dead() {
        if (alive) {
            return false;
        } else {
            return new Date().getTime() - deadTime.getTime() > 2000;
        }
    }
}
