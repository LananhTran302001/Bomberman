package bomberman.entities.enermies;

import bomberman.Renderer;
import bomberman.animation.BalloomAnimation;
import bomberman.constants.Direction;
import bomberman.entities.Sprite;
import bomberman.entities.Vector2;

public class Balloom extends Sprite {

    BalloomAnimation balloomAnimation = new BalloomAnimation();

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
        layer = 3;
    }

    public void draw() {
        Renderer.playAnimation(balloomAnimation.getMoveLeft(), 3, position, size);
    }

    public void update() {

    }

    public void die() {

    }

    public void move(int step, Direction direction) {

    }

    public boolean isPlayerCollideFriendly() {
        return false;
    }
}
