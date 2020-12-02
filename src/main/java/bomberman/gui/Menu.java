package bomberman.gui;

import bomberman.GameLoop;
import bomberman.constants.GameConstants;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class Menu {
    HBox menuBar = new HBox(10);
    Button playButton = new Button("Play");
    Button pauseButton = new Button("Pause");
    Button soundButton = new Button("Sound");

    public Menu() {
        playButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playButton.setDisable(true);
                pauseButton.setDisable(false);
                GameLoop.playGame();
            }
        });
        playButton.setFocusTraversable(false);

        pauseButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                playButton.setDisable(false);
                pauseButton.setDisable(true);
                GameLoop.pauseGame();
            }
        });
        pauseButton.setFocusTraversable(false);

        soundButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

            }
        });

        menuBar.getChildren().addAll(playButton, pauseButton);
        menuBar.setPrefHeight(GameConstants.MENU_HEIGHT);
    }

    public HBox getMenuBar() {
        return menuBar;
    }

}
