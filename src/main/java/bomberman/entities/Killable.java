package bomberman.entities;

public interface Killable {

    // check killed => move
    // check dead => remove

    public boolean killed();
    public void setKilledAnimation();
    public boolean dead();

}
