package bomberman.entities.player;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Flame;
import bomberman.entities.enermies.Enemy;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Date;


public class Player extends Sprite {

    private int lives = 3;
    private Date deadTime;
    private Date lastHitTime;

    private Direction direction = Direction.DOWN;
    private Image[] currentFrames = PlayerAnimation.getMoveDown();

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

    public void init() {
        lives = 3;
        alive = true;
        this.setSize(30, 38);
        setLayer(GameConstants.PLAYER_LAYER);
        lastHitTime = new Date();
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
        alive = false;
        deadTime = new Date();
        currentFrames = PlayerAnimation.getDead();
    }

    public void shock() {
        if (lives > 1) {
            if (new Date().getTime() - lastHitTime.getTime() > 1000) {
                lives--;
                setKilledAnimation();
                lastHitTime = new Date();
            }

        } else {
            die();
        }
    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public boolean checkCollision(Vector2 _p, Vector2 _direction) {
        setBound(new Rectangle2D(_p.getX() + 5, _p.getY() + 20, size.getX() - 10, size.getY() - 22));
        for (Entity e : GameLoop.getEntities()) {
            if (!e.isPlayerCollideFriendly() && e != this && collideWith(e)) {
                // easier to take items than using map method
                System.out.println("Collide with " + e.getName());
                return true;
            }
        }
        return false;
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
        Image temp = currentFrames[0];
        currentFrames = new Image[1];
        currentFrames[0] = temp;
    }

    public void setKilledAnimation() {
        currentFrames = PlayerAnimation.getShock();
    }


    // check killed => move
    // check dead => remove
    public boolean killed() {
        /*setBound(new Rectangle2D(getPosition().getX() + 5, getPosition().getY() + 5,
                size.getX() - 10, size.getY() - 10));

        for (Entity e : GameLoop.getEntities()) {
            if (((e instanceof Enemy) || (e instanceof Flame)) && collideWith(e)) {
                return true;
            }
        }
        return false;*/
        alive = lives > 0;
        return alive;
    }

    public boolean dead() {
        if (alive) {
            return false;
        } else {
            return new Date().getTime() - deadTime.getTime() > 1000;
        }
    }
}
