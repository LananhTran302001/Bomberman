package bomberman.controller;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class EventHandle {
    public static char lastKeyPressed;
    public static char lastKeyReleased;
    public static ArrayList<KeyCode> inputKeys = new ArrayList<KeyCode>();

    public static void a() {

    }

    public boolean isKeyPressed(KeyCode key) {
        return inputKeys.contains(key);
    }

    public static List getInputList() {
        return inputKeys;
    }


}
