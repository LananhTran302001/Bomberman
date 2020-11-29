package bomberman.entities.tiles.items;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.entities.Entity;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;

import java.util.Date;

public abstract class Item extends Entity {

    private boolean alive = true;
    private Date startPowerTime;
    private final int POWER_TIME = 15000;

    public Item() {
        setLayer(GameConstants.ITEM_LAYER);
    }

    public void draw() {
        if (alive) {
            Renderer.renderTileImage(position, image);
        }
    }

    public void update() {
        alive = checkCollision();
    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public boolean checkCollision() {
        if (alive) {
            this.setBound(new Rectangle2D(position.getX() + 2, position.getY() + 2, size.getX() - 4, size.getY() - 4));
            if (collideWith(GameScene.getPlayer())) {
                startPowerTime = new Date();
                doPower();
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean outOfTime() {
        if (!alive) {
            if (new Date().getTime() - startPowerTime.getTime() > POWER_TIME) {
                stopPower();
                return true;
            }
        }
        return false;
    }

    abstract void doPower();
    abstract void stopPower();

}
