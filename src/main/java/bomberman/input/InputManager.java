package bomberman.input;

import bomberman.constants.Direction;
import bomberman.constants.GameConstants;
import bomberman.entities.bomb.BlackBomb;
import bomberman.entities.player.Player;
import bomberman.scenes.EasyLevel;
import javafx.scene.input.KeyCode;

import java.util.List;

public class InputManager {

    public static void handlePlayerInput() {
        List keyboardInput = GameEventHandle.getInputList();
        Player player = EasyLevel.getPlayer();

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
            int x = player.getPosition().getX();
            int y = player.getPosition().getY();
            x = Math.round(x / (float)GameConstants.TILE_SIZE) * GameConstants.TILE_SIZE;
            y = Math.round(y / (float)GameConstants.TILE_SIZE) * GameConstants.TILE_SIZE;
            EasyLevel.addEntity(new BlackBomb(x, y));
        }
        if (keyboardInput.size() < 1) {
            player.stopAnimation();
        }
    }
}
