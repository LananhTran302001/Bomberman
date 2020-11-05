package bomberman.entities;

import javafx.scene.image.Image;

public abstract class Entity extends BoundedRect {

    protected Image image;
    protected int layer;

    public Entity() {
        super();
        layer = 0;
    }

    public Entity(Vector2 pos, Vector2 size) {
        super(pos, size);
    }

    public Entity(Vector2 size, String imgPath, int layer) {
        super(new Vector2(0, 0), size);
        this.image = new Image(imgPath);
        this.layer = layer;
    }

    public Entity(int width, int height, String imgPath, int layer) {
        super(0, 0, width, height);
        this.image = new Image(imgPath);
        this.layer = layer;
    }

    public Entity(Vector2 pos, Vector2 size, String imgPath, int layer) {
        super(pos, size);
        this.image = new Image(imgPath);
        this.layer = layer;
    }

    public Entity(int x, int y, int width, int height, String imgPath, int layer) {
        this(new Vector2(x, y), new Vector2(width, height), imgPath, layer);
    }


    public void loadImage(String pth) {
        try {
            image = new Image(pth);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage(String pth) {
        this.image = new Image(pth);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public abstract void init();

    public abstract void draw();

    public abstract void update();
}
