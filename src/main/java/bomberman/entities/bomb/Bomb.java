package bomberman.entities.bomb;

import bomberman.constants.GameConstants;
import bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    protected Date startDate;
    protected STATE state;
    protected static List<Bomb> bombsList = new ArrayList<Bomb>();

    private enum STATE {
        INACTIVE,
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(int x, int y) {
        startDate = new Date();
        state = STATE.ACTIVE;
    }

    public Bomb() {
        super(50, 50, GameConstants.BOMB_IMG, 1);
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

}
