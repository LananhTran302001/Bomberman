package bomberman.entities.tiles.items;

import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Vector2;

public class SpeedUp extends Item {

    public SpeedUp() {
        super();
        init();
    }

    public SpeedUp(Vector2 position) {
        super();
        setPosition(position);
        init();
    }

    public String getName() {
        return "Item Speed up";
    }

    public void init() {
        setImage(GameImages.ITEM_SPEED_UP_IMG);
    }

    void doPower() {
        GameConstants.STEP_LENGTH = 4;
    }

    void stopPower() {
        GameConstants.STEP_LENGTH = 2;
    }
}
