package bomberman.controller;

import java.util.List;

public class InputManager {

    private final InputKey[] keyMapping;
    private final boolean[] keyState;

    public InputManager(InputKey[] keyMapping, boolean[] keyState) {
        this.keyMapping = keyMapping;
        this.keyState = keyState;
    }


    public static void handlePlayerInput() {
        List keyboardInput = EventHandle.getInputList();
    }
}
