package bomberman.entities.player;

import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.gui.Sound;
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
    Sound shockSound = new Sound(GameSounds.PLAYER_SHOCK);
    Sound deadSound = new Sound(GameSounds.PLAYER_DEAD);
    Sound stepSound = new Sound(GameSounds.PLAYER_STEP, true);

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

    public void setToBeginPosition() {
        setPosition(new Vector2(32, 20));
    }

    public void draw() {
        if (currentFrames.length > 1) {
            Renderer.playAnimation(currentFrames, 6, position, size);
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
        stepSound.stop();
        shockSound.stop();
        deadSound.play();
        draw();

    }

    public boolean lagging() {
        if (lastHitTime == null) {
            return false;
        }

        long lagTime = GameConstants.PLAYER_LAG_TIME - (new Date().getTime() - lastHitTime.getTime());
        if (lagTime > 0) {
            return true;

        } else if (lagTime == 0) {
            setPosition(new Vector2(32, 20));
        }
        return false;
    }

    public void shock() {
        if (alive) {
            if (!lagging()) {

                if (lives > 1) {
                    lives--;
                    setKilledAnimation();
                    lastHitTime = new Date();
                    shockSound.play();
                    draw();

                } else {
                    die();
                }
            }
        }
    }

    public void addLives() {
        if (lives < 3 && lives > 0) {
            lives++;
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
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(25, 10))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 10))));

        } else {
            // Go Down
            return barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(6, 35))))
                    || barrierTile(Vector2.getPositionInMap(Vector2.add(_p, new Vector2(25, 35))));
        }

    }

    public void move(int _step, Direction _direction) {
        if (_direction == null) {
            return;
        }

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
            if (!stepSound.isPlaying()) {
                stepSound.play();
            }
            this.setPosition(newPosition);
        }

    }

    public void stop() {
        setCurrentFrames(currentFrames[0]);
        stepSound.stop();
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
            return new Date().getTime() - deadTime.getTime() > GameConstants.PLAYER_DEAD_TIME;
        }
    }

    @Override
    public boolean notUsed() {
        return dead();
    }
}
