package bomberman.controller;

import javafx.scene.input.KeyCode;

public enum InputKey {
    UP(KeyCode.UP, KeyCode.W),
    DOWN(KeyCode.DOWN, KeyCode.S),
    LEFT(KeyCode.LEFT, KeyCode.A),
    RIGHT(KeyCode.RIGHT, KeyCode.D);

    InputKey(KeyCode a, KeyCode b) {

    }
}
