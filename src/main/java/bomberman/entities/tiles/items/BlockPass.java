package bomberman.entities.tiles.items;

import bomberman.constants.GameImages;
import bomberman.entities.Vector2;
import bomberman.entities.player.Player;
import bomberman.entities.tiles.Brick;

public class BlockPass extends Item {

    public BlockPass() {
        super();
        init();
    }

    public BlockPass(Vector2 position) {
        super();
        setPosition(position);
        init();
    }


    public String getName() {
        return "Item block-pass";
    }

    public void init() {
        setImage(GameImages.ITEM_BLOCK_PASS_IMG);
    }

    void doPower() {
        Brick.setPlayerCollideFriendly(true);
        Player.setCanWalkOnBrick(true);
    }

    void stopPower() {
        Brick.setPlayerCollideFriendly(false);
        Player.setCanWalkOnBrick(false);
    }

}
