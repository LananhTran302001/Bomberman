package bomberman.entities;

public interface Perceptive {

    public int observe(int radiusArea);

    public Vector2 findDirection();
}
