package bomberman.entities;

import bomberman.constants.Direction;

public interface Movable {

    public void move(int step, Direction direction);
}
