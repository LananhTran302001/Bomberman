package bomberman.entities;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;

public abstract class AnimatedSprite extends Sprite {

    public AnimatedImage animationLeft;
    public AnimatedImage animationRight;
    public AnimatedImage animationUp;
    public AnimatedImage animationDown;

    public abstract void setAnimationLeft();
    public abstract void setAnimationRight();
    public abstract void setAnimationUp();
    public abstract void setAnimationDown();

    public void init() {
        setAnimationUp();
        setAnimationDown();
        setAnimationLeft();
        setAnimationRight();
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


}
