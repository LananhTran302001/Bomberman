package bomberman.entities.bomb;

import bomberman.entities.Entity;
import bomberman.entities.Vector2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Bomb extends Entity {

    Date startDate;
    int range;
    //static List<Bomb> bombsList = new ArrayList<Bomb>();

    protected enum STATE {
        ACTIVE,
        EXPLODING,
        DEAD
    }

    public Bomb(Vector2 position) {
        super(position);
        startDate = new Date();
    }

    public Bomb(int x, int y) {
        super(x, y);
        startDate = new Date();
    }

    public String getName() {
        return "Bomb";
    }

    public void setRange(int a) {
        range = a;
    }

    @Override
    public boolean isPlayerCollideFriendly() {
        return true;
    }

    public void initFlame() {

    }

    public STATE getState() {
        long lifeTime = new Date().getTime() - startDate.getTime();
        if (lifeTime > 3000) {
            return STATE.DEAD;
        } else if (lifeTime > 2000) {
            return STATE.EXPLODING;
        } else {
            return STATE.ACTIVE;
        }
    }

    public boolean dead() {
        return getState() == STATE.DEAD;
    }

    public abstract void explode();

}
