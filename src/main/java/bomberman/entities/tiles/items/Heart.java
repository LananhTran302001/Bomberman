package bomberman.entities.tiles.items;

import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Vector2;
import bomberman.scenes.GameScene;

public class Heart extends Item {
    public Heart() {
        super();
        init();
    }

    public Heart(Vector2 position) {
        super();
        setPosition(position);
        init();
    }

    public String getName() {
        return "Item heart";
    }

    public void init() {
        setImage(GameImages.ITEM_HEART_IMG);
    }


    void doPower() {
        GameScene.getPlayer().addLives();
    }

    void stopPower() {

    }
}
