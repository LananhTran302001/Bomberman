package bomberman.scenes;

import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;

import bomberman.gui.Sound;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class StartScene extends StaticScene{

    private boolean loading = false;
    private Label gameName = new Label(GameConstants.GAME_NAME);
    private Label level = new Label();
    private Button playButton = new Button("Play");
    private Button exitButton = new Button("Exit");
    private final Sound levelStartSound = new Sound(GameSounds.LEVEL_START);

    public StartScene() {
        super();
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
}
