package bomberman.entities.player;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.AnimatedImage;
import bomberman.entities.AnimatedSprite;

public class Player extends AnimatedSprite {



    public void die() {

    }

    public void setAnimationLeft() {
        animationLeft = new AnimatedImage(GameConstants.getPlayerImageUrl(Direction.LEFT), 2);
    }

    public void setAnimationRight() {
        animationRight = new AnimatedImage(GameConstants.getPlayerImageUrl(Direction.RIGHT), 2);
    }

    public void setAnimationUp() {
        animationUp = new AnimatedImage(GameConstants.getPlayerImageUrl(Direction.UP), 2);
    }

    public void setAnimationDown() {
        animationDown = new AnimatedImage(GameConstants.getPlayerImageUrl(Direction.DOWN), 2);
    }
}
