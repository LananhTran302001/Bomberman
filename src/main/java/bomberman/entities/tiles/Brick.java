package bomberman.entities.tiles;

import bomberman.Renderer;
import bomberman.animation.BrickAnimation;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Breakable;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.Bomb;
import bomberman.scenes.GameScene;
import javafx.geometry.Rectangle2D;

import java.util.Date;


public class Brick extends Entity implements Breakable {

    private boolean playerCollideFriendly = false;
    private boolean intact = true;
    private Date brokenTime;

    public Brick(int x, int y) {
        super(x, y);
        init();
    }

    public String getName() {
        return "Brick";
    }

    public Brick(Vector2 position) {
        super( position);
        init();
    }

    public void init() {
        setImage(GameImages.BRICK_IMG);
        setLayer(GameConstants.OBSTACLE_LAYER);
    }

    public void draw() {
        if (intact) {
            Renderer.renderTileImage(position, image);
        } else {
            Renderer.playAnimation(BrickAnimation.getExplosionBrickAnimation(), 4, position, size);
        }
    }

    public void update() {
        intact = checkIntactState();
    }

    protected void setPlayerCollideFriendly(boolean ability) {
        playerCollideFriendly = ability;
    }

    public boolean isPlayerCollideFriendly() {
        return playerCollideFriendly;
    }

    private boolean checkIntactState() {
        if (intact) {
            this.setBound(new Rectangle2D(position.getX() - 2, position.getY() - 2, size.getX() + 4, size.getY() + 4));
            for (Bomb b : GameScene.getBombList()) {
                if (b.hitFlame(this)) {
                    brokenTime = new Date();
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isBroken() {
        if (intact) {
            return false;
        } else {
            return new Date().getTime() - brokenTime.getTime() > 1000;
        }
    }
}
