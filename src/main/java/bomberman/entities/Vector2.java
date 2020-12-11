package bomberman.entities;

import bomberman.constants.GameConstants;

public class Vector2 {

    protected int x;
    protected int y;

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void setVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 getVector2() {
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * return x * x + y * y
     */
    public int getPowLength() {
        return x * x + y * y;
    }

    public boolean equals(Vector2 other) {
        return (this.x == other.getX()) && (this.y == other.getY());
    }

    public void add(Vector2 other) {
        if (other != null) {
            this.x += other.getX();
            this.y += other.getY();
        }
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        int addX = v1.getX() + v2.getX();
        int addY = v1.getY() + v2.getY();
        return new Vector2(addX, addY);
    }

    public void multiple(int num) {
        this.x *= num;
        this.y *= num;
    }

    public void multiple(Vector2 other) {
        if (other != null) {
            this.x *= other.getX();
            this.y *= other.getY();
        }
    }

    public static Vector2 multiple(Vector2 v, int num) {
        int mulX = v.getX() * num;
        int mulY = v.getY() * num;
        return new Vector2(mulX, mulY);
    }

    public static Vector2 getPositionInMap(Vector2 realPosition, Vector2 realSize) {
        int mapX = realPosition.getX() + realSize.getX() - GameConstants.TILE_SIZE;
        int mapY = realPosition.getY() + realSize.getY() - GameConstants.TILE_SIZE;
        mapX = Math.round(mapX / (float)GameConstants.TILE_SIZE);
        mapY = Math.round(mapY / (float)GameConstants.TILE_SIZE);

        return new Vector2(mapX, mapY);
    }

    public static Vector2 getPositionInMap(Vector2 position) {
        int mapX = position.getX() / GameConstants.TILE_SIZE;
        int mapY = position.getY() / GameConstants.TILE_SIZE;
        return new Vector2(mapX, mapY);
    }

    public static Vector2 divide(Vector2 v1, int num) {
        return new Vector2(v1.getX() / num, v1.getY() / num);
    }

}
