package bomberman.input;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.Vector2;
import bomberman.entities.bomb.BlackBomb;
import bomberman.entities.player.Player;
import bomberman.scenes.GameScene;
import javafx.scene.input.KeyCode;

import java.util.List;

public class InputManager {

    public static void handlePlayerInput() {
        List keyboardInput = GameEventHandle.getInputList();
        Player player = GameScene.getPlayer();

        if (!player.dead()) {
            if (keyboardInput.contains(KeyCode.UP) || keyboardInput.contains(KeyCode.W)) {
                player.move(1, Direction.UP);
            }
            if (keyboardInput.contains(KeyCode.LEFT) || keyboardInput.contains(KeyCode.A)) {
                player.move(1, Direction.LEFT);
            }
            if (keyboardInput.contains(KeyCode.RIGHT) || keyboardInput.contains(KeyCode.D)) {
                player.move(1, Direction.RIGHT);
            }
            if (keyboardInput.contains(KeyCode.DOWN) || keyboardInput.contains(KeyCode.S)) {
                player.move(1, Direction.DOWN);
            }

            if (keyboardInput.contains(KeyCode.SPACE)) {
                Vector2 bombPosition = Vector2.getPositionInMap(player.getPosition(), player.getSize());
                bombPosition.multiple(GameConstants.TILE_SIZE);
                GameScene.addEntity(new BlackBomb(bombPosition));
            }
            if (keyboardInput.size() < 1) {
                player.stopAnimation();
            }
        }
    }
}
