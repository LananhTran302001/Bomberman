package bomberman.entities.enermies;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.animation.BalloomAnimation;
import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Grass;
import bomberman.scenes.EasyLevel;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;

public class Balloom extends Sprite {

    Vector2 directionVector;
    Image[] currentAnimation;
    int row;

    public Balloom(Vector2 position) {
        this.setPosition(position);
        row = position.getY() / GameConstants.TILE_SIZE;
        init();
    }

    public Balloom(int x, int y) {
        this(new Vector2(x, y));
    }

    public String getName() {
        return "Balloom";
    }

    public void init() {
        setLayer(3);
        int randomNum = new Random().nextInt() % 2;
        if (randomNum == 0) {
            // init right direction
            directionVector = new Vector2(1, 0);
            currentAnimation = BalloomAnimation.getMoveRight();
        } else {
            // init left direction
            directionVector = new Vector2(-1, 0);
            currentAnimation = BalloomAnimation.getMoveLeft();
        }
    }

    public void draw() {
        Renderer.playAnimation(currentAnimation, 2, position, size);
    }

    public void update() {
        move();
    }


    private boolean checkCollision(Vector2 p) {
        int i = p.getY() / GameConstants.TILE_SIZE;
        int j = p.getX() / GameConstants.TILE_SIZE;
        if (directionVector.getX() > 0) {
            j++;
        }
        return EasyLevel.getStaticMapAt(i, j) != ' ';
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
            currentAnimation = BalloomAnimation.getMoveRight();
        } else {
            currentAnimation = BalloomAnimation.getMoveLeft();
        }
    }

    public boolean isPlayerCollideFriendly() {
        return false;
    }

    public boolean dead() {
        for (Bomb b : EasyLevel.getBombList()) {
            if (b.hitFlame(this)) {
                currentAnimation = BalloomAnimation.getDead();
                return true;
            }
        }

        return false;
    }
}
