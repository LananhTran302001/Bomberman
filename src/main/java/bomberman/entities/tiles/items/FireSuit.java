package bomberman.entities.tiles.items;

import bomberman.constants.GameImages;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Flame;

public class FireSuit extends Item {

    public FireSuit()  {
        super();
        init();
    }

    public FireSuit(Vector2 position) {
        super();
        setPosition(position);
        init();
    }

    public String getName() {
        return "Item Fire-suit";
    }

    public void init() {
        setImage(GameImages.ITEM_FIRE_SUIT_IMG);
    }

    void doPower() {
        Flame.setKillable(false);
    }

    void stopPower() {
        Flame.setKillable(true);
    }

}
