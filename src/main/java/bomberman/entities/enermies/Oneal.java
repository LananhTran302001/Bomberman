package bomberman.entities.enermies;

import bomberman.constants.Direction;
import bomberman.entities.AISprite;
import bomberman.entities.Vector2;


public class Oneal extends AISprite {
    public String getName() {
        return "Oneal";
    }

    public void init() {
        setLayer(3);
    }

    public void draw() {

    }

    public void update() {

    }

    public boolean dead() {
        return false;
    }

    public void move(int step, Direction direction) {

    }

    public int observe(int radiusArea) {
        return 0;
    }

    public Vector2 findDirection() {
        return null;
    }

    public boolean isPlayerCollideFriendly() {
        return false;
    }

}
