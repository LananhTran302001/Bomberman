package bomberman.scenes;

import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;

import bomberman.gui.Sound;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class StartScene {

    private static Scene scene;
    private static Group root;
    private static Label gameName = new Label(GameConstants.GAME_NAME);
    private static VBox background = new VBox(50);
    private static Button playButton = new Button("Play");
    private static Button exitButton = new Button("Exit");
    private static final Sound levelStartSound = new Sound(GameSounds.LEVEL_START);

    private static void initScene() {
        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        root.getChildren().addAll(background);

        background.setPrefWidth(GameConstants.SCENE_WIDTH);
        background.setPrefHeight(GameConstants.SCENE_HEIGHT);
        background.setStyle("-fx-background-color: #111111");
        background.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(50);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.getChildren().addAll(playButton, exitButton);

        playButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                levelStartSound.stop();
                Stage stage = (Stage)exitButton.getScene().getWindow();
                GameScene.startScene();
                stage.setScene(GameScene.getScene());
            }
        });

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                levelStartSound.stop();
                Stage stage = (Stage)exitButton.getScene().getWindow();
                stage.close();
            }
        });

        gameName.setTextFill(Color.WHITE);
        gameName.setFont(Font.font(30));
        gameName.setPrefSize(200, 30);

        background.getChildren().addAll(gameName, buttons);

        levelStartSound.play();
    }

    public static Scene getScene() {
        initScene();
        return scene;
    }
}
