package entities.bomb;

import constants.GameConstants;
import entities.Entity;

import java.util.Date;

public abstract class Bomb extends Entity {

    protected Date startDate;
    protected STATE state;

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
}
