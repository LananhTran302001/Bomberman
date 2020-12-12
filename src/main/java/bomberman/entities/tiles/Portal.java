package bomberman.entities.tiles;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.constants.GameConstants;
import bomberman.constants.GameImages;
import bomberman.entities.Entity;
import bomberman.entities.Vector2;
import bomberman.gui.Sound;
import bomberman.input.GameEventHandle;
import bomberman.scenes.GameScene;
import bomberman.scenes.StartScene;
import bomberman.scenes.VictoryScene;
import javafx.geometry.Rectangle2D;


public class Portal extends Entity {

    private boolean loading = false;

    public Portal() {
        super();
        init();
    }

    public Portal(Vector2 position) {
        super(position);
        init();
    }

    public String getName() {
        return "Portal";
    }

    public void init() {
        setImage(GameImages.PORTAL_IMG);
        setLayer(GameConstants.OBSTACLE_LAYER);
        setBound(new Rectangle2D(position.getX() + 10, position.getY() + 10, size.getX() - 20, size.getY() - 20));
    }

    public void draw() {
        Renderer.renderTileImage(position, image);
    }

    public void update() {
        if (collideWith(GameScene.getPlayer()) && GameScene.killedAllEnemies() && !loading) {
            GameEventHandle.clear();
            if (GameScene.getLevel() >= 5) {
                new VictoryScene().show();
            } else {
                new StartScene().show();
            }
            loading = true;
        }
    }


    public boolean isPlayerCollideFriendly() {
        return false;
    }

    public boolean notUsed() {
        return false;
    }
}
