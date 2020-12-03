package bomberman.entities.tiles.items;

import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.entities.Entity;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Date;

public abstract class Item extends Entity {

    private boolean alive = true;
    private Date startPowerTime;
    MediaPlayer pickedSound = new MediaPlayer(new Media(GameSounds.PICK_ITEM));

    public Item() {
        setLayer(GameConstants.ITEM_LAYER);
    }

    public void draw() {
        if (alive) {
            Renderer.renderTileImage(position, image);
        }
    }

    public void update() {
        alive = checkPlayerCollision();
    }

    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public boolean checkPlayerCollision() {
        if (alive) {
            this.setBound(new Rectangle2D(position.getX() + 10, position.getY() + 10, size.getX() - 20, size.getY() - 20));
            if (collideWith(GameScene.getPlayer())) {
                System.out.println("Item collected!");
                startPowerTime = new Date();
                GameSounds.playSound(pickedSound);
                doPower();
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean outOfTime() {
        if (!alive) {
            if (new Date().getTime() - startPowerTime.getTime() > GameConstants.ITEM_POWER_TIME) {
                stopPower();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean notUsed() {
        return outOfTime();
    }

    abstract void doPower();
    abstract void stopPower();

}
