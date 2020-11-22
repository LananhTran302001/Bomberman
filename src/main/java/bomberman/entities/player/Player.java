package bomberman.entities.player;

import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import javafx.scene.image.Image;


public class Player extends Sprite {

    private PlayerAnimation playerAnimation = new PlayerAnimation();
    private Direction direction = Direction.DOWN;
    private int health;
    private Image[] currentFrames = playerAnimation.getMoveDown();

    public Player(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Player(int x, int y) {
        this(new Vector2(x, y));
    }

    public void init() {
        this.setSize(32, 32);
        health = 100;
        layer = 3;
    }

    public void draw() {
        Renderer.playAnimation(currentFrames, 3, position, size);
    }

    public void update() {

    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }


    public void move(int step, Direction direction) {
        Vector2 directionVector = new Vector2();

        switch (direction) {
            case UP:
                directionVector = new Vector2(0, -1);
                currentFrames = playerAnimation.getMoveUp();
                break;

            case DOWN:
                directionVector = new Vector2(0, 1);
                currentFrames = playerAnimation.getMoveDown();
                break;

            case LEFT:
                directionVector = new Vector2(-1, 0);
                currentFrames = playerAnimation.getMoveLeft();
                break;

            case RIGHT:
                directionVector = new Vector2(1, 0);
                currentFrames = playerAnimation.getMoveRight();
                break;
        }

        this.setPosition(this.getPosition().add(directionVector.multiple(step * GameConstants.STEP_LENGTH)));
    }

    public void move(Direction d) {
        move(1, d);
    }

    public void die() {

    }
}
