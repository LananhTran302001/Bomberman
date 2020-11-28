package bomberman.entities.enermies.ai;

import bomberman.entities.Vector2;

public class Distance {
    protected Vector2 point;
    protected int distance;

    public Distance(Vector2 point, int distance) {
        this.point = point;
        this.distance = distance;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

}
