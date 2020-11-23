package bomberman.entities.enermies;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.animation.BalloomAnimation;
import bomberman.constants.Direction;
import bomberman.entities.Entity;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.entities.tiles.Grass;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;

public class Balloom extends Sprite {

    Vector2 directionVector;
    BalloomAnimation balloomAnimation = new BalloomAnimation();
    Image[] currentAnimation;

    public Balloom(Vector2 position) {
        this.setPosition(position);
        init();
    }

    public Balloom(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Balloom";
    }

    public void init() {
        layer = 2;
        int randomNum = new Random().nextInt() % 2;
        if (randomNum == 0) {
            // init right direction
            directionVector = new Vector2(1, 0);
            currentAnimation = balloomAnimation.getMoveRight();
        } else {
            // init left direction
            directionVector = new Vector2(-1, 0);
            currentAnimation = balloomAnimation.getMoveLeft();
        }
    }

    public void draw() {
        move();
        Renderer.playAnimation(currentAnimation, 2, position, size);
    }

    public void update() {

    }

    public void die() {

    }

    private boolean checkCollision(Vector2 p) {
        setBound(new Rectangle2D(p.getX(), p.getY(), size.getX(), size.getY()));
        for (Entity e : GameLoop.getEntities()) {
            if (e != this && !(e instanceof Grass) && collideWith(e)) {
                /*if (e.getClass().isInstance(Brick.class) || e.getClass().isInstance(Wall.class)) {
                    return true;
                }*/
                System.out.println("Balloom collides with" + e.getName());
                return true;
            }
        }
        return false;
    }

    public void move(int step, Direction direction) {
    }

    public void move() {
        Vector2 newPosition = new Vector2(position).add(directionVector);
        if (!checkCollision(newPosition)) {
            this.setPosition(newPosition);
        } else {
            changeDirection();
        }
    }

    private void changeDirection() {
        directionVector = directionVector.multiple(-1);
        if (directionVector.getX() > 0) {
            currentAnimation = balloomAnimation.getMoveRight();
        } else {
            currentAnimation = balloomAnimation.getMoveLeft();
        }
    }

    public boolean isPlayerCollideFriendly() {
        return false;
    }
}
