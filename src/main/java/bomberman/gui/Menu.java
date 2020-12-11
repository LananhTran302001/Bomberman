package bomberman.gui;

import bomberman.GameLoop;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class Menu {
    HBox menuBar = new HBox(10);

    Button playButton = new Button("Play");
    Button pauseButton = new Button("Pause");
    ToggleButton soundButton = new ToggleButton("Sound");



    Sound pauseSound = new Sound(GameSounds.PAUSE);

    public Menu() {
        playButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playButton.setDisable(true);
                pauseButton.setDisable(false);
                pauseSound.stop();
                GameLoop.playGame();
            }
        });
        playButton.setFocusTraversable(false);

        pauseButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playButton.setDisable(false);
                pauseButton.setDisable(true);
                pauseSound.play();
                GameLoop.pauseGame();
            }
        });
        pauseButton.setFocusTraversable(false);

        soundButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (soundButton.isSelected()) {
                    Sound.turnOnSound();
                } else {
                    Sound.turnOffSound();
                }
            }
        });
        soundButton.setFocusTraversable(false);

        menuBar.getChildren().addAll(new Label("\tBOMBERMAN"), playButton, pauseButton, soundButton);
        menuBar.setPrefHeight(GameConstants.MENU_HEIGHT + 5);
    }

    public HBox getMenuBar() {
        return menuBar;
    }

}
