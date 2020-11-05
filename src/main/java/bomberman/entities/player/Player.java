package bomberman.entities.player;

import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.animation.AnimatedImage;
import bomberman.entities.AnimatedSprite;
import bomberman.entities.Vector2;

public class Player extends AnimatedSprite {

    private Direction direction;
    private int health = 100;

    public AnimatedImage animation;

    public Player() {
        health = 100;
        this.setLayer(0);
    }

    public Player(Vector2 position) {
        this.setPosition(position);
        this.setLayer(0);
    }

    public void setAnimation(AnimatedImage animatedImage) {
        if (animatedImage != null) {
            this.animation = animatedImage;
        }
    }

    public void init() {

    }

    public void draw() {

    }

    public void update() {

    }


    public void move(int step, Direction direction) {
        Vector2 directionVector = new Vector2();

        switch (direction) {
            case UP:
                directionVector = new Vector2(0, -1);
                direction = Direction.UP;
                setAnimation(PlayerAnimation.getGoUpAnim());
                break;

            case DOWN:
                directionVector = new Vector2(0, 1);
                direction = Direction.DOWN;
                setAnimation(PlayerAnimation.getGoDownAnim());
                break;

            case LEFT:
                directionVector = new Vector2(-1, 0);
                direction = Direction.LEFT;
                setAnimation(PlayerAnimation.getGoLeftAnim());
                break;

            case RIGHT:
                directionVector = new Vector2(1, 0);
                direction = Direction.RIGHT;
                setAnimation(PlayerAnimation.getGoRightAnim());
                break;
        }

        this.setPosition(this.getPosition().add(directionVector.multiple(step * GameConstants.STEP_LENGTH)));
    }

    public void move(Direction direction) {
        move(1, direction);
    }

    public void move(int step) {
        move (step, direction);
    }



    public void die() {

    }
}
