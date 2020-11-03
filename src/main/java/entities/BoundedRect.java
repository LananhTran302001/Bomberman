package entities;

import javafx.geometry.Rectangle2D;

public class BoundedRect {

    protected Vector2 position;
    protected Vector2 size;
    protected Rectangle2D bound;

    public BoundedRect() {
        position = new Vector2();
        size = new Vector2();
        bound = new Rectangle2D(position.x, position.y, size.x, size.y);
    }

    public BoundedRect(int x, int y, int width, int height) {
        position = new Vector2(x, y);
        size = new Vector2(width, height);
        bound = new Rectangle2D(x, y, width, height);
    }

    public BoundedRect(Vector2 pos, int width, int height) {
        position = new Vector2(pos);
        size = new Vector2(width, height);
        bound = new Rectangle2D(pos.x, pos.y, width, height);
    }

    public BoundedRect(int x, int y, Vector2 size) {
        position = new Vector2(x, y);
        size = new Vector2(size);
        bound = new Rectangle2D(position.x, position.y, size.x, size.y);
    }

    public BoundedRect(Vector2 pos, Vector2 size) {
        this.position = new Vector2(pos);
        this.size = new Vector2(size);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getWidth() {
        return size.x;
    }

    public int getHeight() {
        return size.y;
    }

    public  Vector2 getSize() {
        return size;
    }

    public void setSize(int width, int height) {
        size = new Vector2(width, height);
    }

    public Rectangle2D getBound() {
        return bound;
    }

    public void setBound(Rectangle2D bound) {
        this.bound = bound;
    }

    public boolean collideWith(BoundedRect other) {
        return bound.intersects(other.getBound());
    }
}
