package bomberman.entities;

import bomberman.constants.GameConstants;
import javafx.scene.image.Image;

public abstract class Entity extends BoundedRect {

    protected Image image;
    protected int layer;

    public Entity() {
        super();
    }

    public Entity(Vector2 pos, Vector2 size) {
        super(pos, size);
    }

    public Entity(Vector2 position) {
        super(position, new Vector2(GameConstants.TILE_SIZE, GameConstants.TILE_SIZE));
    }

    public Entity(int x, int y) {
        this(new Vector2(x, y));
    }

    public Entity(int x, int y, int width, int height) {
        this(new Vector2(x, y), new Vector2(width, height));
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage(String pth) {
        try {
            this.image = new Image(pth);
        } catch (NullPointerException e) {
            System.out.println("Can not load entity's image");
        }
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

    public abstract boolean isPlayerCollideFriendly();
}
