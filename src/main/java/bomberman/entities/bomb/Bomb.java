package bomberman.entities.bomb;

import bomberman.entities.Entity;
import bomberman.entities.Vector2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    protected Date startDate;
    protected STATE state;
    protected static List<Bomb> bombsList = new ArrayList<Bomb>();

    private enum STATE {
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(Vector2 position) {
        super(position);
        startDate = new Date();
        state = STATE.ACTIVE;
    }

    public Bomb(int x, int y) {
        super(x, y);
        startDate = new Date();
        state = STATE.ACTIVE;
    }

    public String getName() {
        return "Bomb";
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public STATE checkBombState() {
        if (new Date().getTime() > 2000 + startDate.getTime()) {
            return STATE.DEAD;
        }
        return STATE.ACTIVE;
    }

    public boolean isAlive() {
        return state != STATE.DEAD;
    }

    public abstract void explode();

}
