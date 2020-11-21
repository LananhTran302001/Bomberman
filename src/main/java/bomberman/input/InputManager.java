package bomberman.input;

import bomberman.constants.Direction;
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
            EasyLevel.addEntity(new BlackBomb(player.getPosition()));
        }
    }
}
