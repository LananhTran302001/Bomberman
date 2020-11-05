package bomberman.animation;

public class PlayerAnimation {

    static AnimatedImage goUp;
    static AnimatedImage goDown;
    static AnimatedImage goRight;
    static AnimatedImage goLeft;
    static AnimatedImage die;

    public static AnimatedImage getGoUpAnim() {
        return goUp;
    }

    public static AnimatedImage getGoDownAnim() {
        return goDown;
    }

    public static AnimatedImage getGoLeftAnim() {
        return goLeft;
    }

    public static AnimatedImage getGoRightAnim() {
        return goRight;
    }


    public static AnimatedImage getDieAnim() {
        return die;
    }
}
