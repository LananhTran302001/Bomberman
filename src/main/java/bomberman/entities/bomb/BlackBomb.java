package bomberman.entities.bomb;

import bomberman.Renderer;
import bomberman.constants.GameImages;
import bomberman.entities.Vector2;

public class BlackBomb extends Bomb {

    public BlackBomb(int x, int y) {
        super(x, y);
        init();
    }

    public BlackBomb(Vector2 position) {
        super(position);
        init();
    }

    public void init() {
        setImage(GameImages.BOMB_IMG);
        setLayer(2);
    }

    public void draw() {
        Renderer.renderImage(position, size, image);
    }

    public void update() {

    }
}
