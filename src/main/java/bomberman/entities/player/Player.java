package bomberman.entities.player;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.animation.PlayerAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


public class Player extends Sprite {

    private Direction direction = Direction.DOWN;
    private int health;
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
        this.setSize(30, 38);
        health = 100;
        setLayer(4);
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

    public boolean checkCollision(Vector2 p) {
        setBound(new Rectangle2D(p.getX() + 5, p.getY() + 20, size.getX() - 10, size.getY() - 22));
        for (Entity e : GameLoop.getEntities()) {
            if (!e.isPlayerCollideFriendly() && e != this && collideWith(e)) {
                System.out.println("Collide with " + e.getName());
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }


    public void move(int step, Direction direction) {
        Vector2 directionVector = new Vector2();

        switch (direction) {
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


        Vector2 newPosition = new Vector2(position).add(directionVector.multiple(step * GameConstants.STEP_LENGTH));
        if (!checkCollision(newPosition)) {
            this.setPosition(newPosition);
        }
    }

    public void move(Direction d) {
        move(1, d);
    }

    public void stopAnimation() {
        Image temp = currentFrames[0];
        currentFrames = new Image[1];
        currentFrames[0] = temp;
    }

    public void die() {

    }
}
