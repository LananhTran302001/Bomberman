package bomberman.entities.player;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.AnimatedImage;
import bomberman.entities.AnimatedSprite;
import bomberman.entities.Vector2;

public class Player extends AnimatedSprite {

    public AnimatedImage animationLeft;
    public AnimatedImage animationRight;
    public AnimatedImage animationUp;
    public AnimatedImage animationDown;

    public void setAnimationImages() {

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
                animationUp.play();
                break;

            case DOWN:
                directionVector = new Vector2(0, 1);
                animationDown.play();
                break;

            case LEFT:
                directionVector = new Vector2(-1, 0);
                animationLeft.play();
                break;

            case RIGHT:
                directionVector = new Vector2(1, 0);
                animationRight.play();
                break;
        }

        this.setPosition(this.getPosition().add(directionVector.multiple(step * GameConstants.STEP_LENGTH)));
    }

    public void move(Direction direction) {
        move(1, direction);
    }


    public void die() {

    }
}
