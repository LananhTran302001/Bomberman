package bomberman.entities.player;

import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;

public class Player extends Sprite {

    private PlayerAnimation playerAnimation = new PlayerAnimation();
    private Direction direction;
    private int health;


    public Player() {
        init();
    }

    public Player(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Player(int x, int y) {
        this(new Vector2(x, y));
    }

    public void init() {
        health = 100;
        layer = 2;
        playerAnimation.setUpImages();
    }

    public void draw() {
        Renderer.playAnimation(playerAnimation.getMoveDown(), 2, position, size);
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
