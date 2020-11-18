package bomberman.entities.player;

import bomberman.Renderer;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;

public class Player extends Sprite {

    private Direction direction;
    private int health = 100;


    public Player() {
        health = 100;
        this.setLayer(0);
    }

    public Player(Vector2 position) {
        this.setPosition(position);
        this.setLayer(0);
    }


    public void init() {

    }

    public void draw() {
        Renderer.renderImage(position, size, image);
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

    public void move(Direction d) {
        move(1, d);
    }

    public void die() {

    }
}
