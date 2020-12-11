package bomberman.scenes;

import bomberman.GameLoop;
import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;

import bomberman.gui.Sound;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class StartScene {

    private static Stage stage;

    private boolean loading = false;
    private Scene scene;
    private Group root;
    private Label gameName = new Label(GameConstants.GAME_NAME);
    private Label level = new Label();
    private VBox background = new VBox(50);
    private Button playButton = new Button("Play");
    private Button exitButton = new Button("Exit");
    private final Sound levelStartSound = new Sound(GameSounds.LEVEL_START);

    public static void setStage(Stage s) {
        stage = s;
    }

    public StartScene() {
        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        root.getChildren().addAll(background);

        background.setPrefWidth(GameConstants.SCENE_WIDTH);
        background.setPrefHeight(GameConstants.SCENE_HEIGHT);
        background.setStyle("-fx-background-color: #111111");
        background.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(30);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.getChildren().addAll(playButton, exitButton);


        playButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!loading) {
                    levelStartSound.stop();
                    GameScene.setNewLevel();
                    stage.setScene(GameScene.getScene());
                    loading = true;
                }
            }
        });
        playButton.setCursor(Cursor.HAND);

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                levelStartSound.stop();
                stage.close();
            }
        });
        exitButton.setCursor(Cursor.HAND);

        gameName.setTextFill(Color.WHITE);
        gameName.setFont(Font.font(30));
        gameName.setPrefSize(200, 30);
        gameName.setAlignment(Pos.CENTER);

        level.setText("LEVEL " + (GameScene.getLevel() + 1));
        level.setTextFill(Color.WHITE);
        level.setFont(Font.font(20));
        level.setAlignment(Pos.TOP_CENTER);

        background.getChildren().addAll(gameName, level, buttons);

        Sound.stopAll();
        levelStartSound.play();
    }

    public Scene getScene() {
        return scene;
    }

    public void show() {
        stage.setScene(scene);
    }
}
