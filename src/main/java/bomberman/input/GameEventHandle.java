package bomberman.input;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class GameEventHandle{
    public static char lastKeyPressed;
    public static char lastKeyReleased;
    public static ArrayList<KeyCode> inputKeys = new ArrayList<KeyCode>();

    public static void attachEventHandle(Scene scene) {
        KeyPressHandle keyPressHandle = new KeyPressHandle();
        KeyReleaseHandle keyReleaseHandle = new KeyReleaseHandle();
        scene.setOnKeyPressed(keyPressHandle);
        scene.setOnKeyReleased(keyReleaseHandle);
    }

    public static boolean isKeyPressed(KeyCode key) {
        return inputKeys.contains(key);
    }

    public static void clear() {
        inputKeys.clear();
    }

    public static List getInputList() {
        return inputKeys;
    }

}

class KeyPressHandle implements EventHandler<KeyEvent> {

    public void handle(KeyEvent event) {
        KeyCode c = event.getCode();
        if (!GameEventHandle.inputKeys.contains(c)) {
            GameEventHandle.inputKeys.add(c);
            System.out.println("Pressing " + c);
        }
    }
}

class KeyReleaseHandle implements EventHandler<KeyEvent> {

    public void handle(KeyEvent event) {
        KeyCode c = event.getCode();
        if (GameEventHandle.inputKeys.contains(c)) {
            GameEventHandle.inputKeys.remove(c);
            System.out.println("Release " + c);
        }
    }

}

