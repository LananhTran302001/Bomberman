package bomberman.entities;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;

public abstract class Sprite extends Entity implements Movable, Killable {


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
                break;

            case DOWN:
                directionVector = new Vector2(0, 1);
                break;

            case LEFT:
                directionVector = new Vector2(-1, 0);
                break;

            case RIGHT:
                directionVector = new Vector2(1, 0);
                break;
        }

        this.setPosition(this.getPosition().add(directionVector.multiple(step * GameConstants.STEP_LENGTH)));

    }

}
