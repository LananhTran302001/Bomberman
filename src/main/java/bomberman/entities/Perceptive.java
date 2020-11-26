package bomberman.entities;

import bomberman.constants.Direction;

public interface Perceptive {

    public Vector2 findDirection(int radiusArea);
}
