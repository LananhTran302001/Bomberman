package bomberman.entities.tiles.items;

import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.BlackBomb;

public class FireUp extends Item {

    public FireUp() {
        super();
        init();
    }

    public FireUp(Vector2 position) {
        super();
        setPosition(position);
        init();
    }

    public String getName() {
        return "Item Fire-up";
    }

    public void init() {
        setImage(GameImages.ITEM_FIRE_UP_IMG);
    }

    void doPower() {
        BlackBomb.setFlameRange(BlackBomb.getFlameRange() + 1);
    }

    void stopPower() {
        BlackBomb.setFlameRange(1);
    }

}
