package bomberman.scenes;

import bomberman.GameLoop;
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

public class VictoryScene extends StaticScene {

    private Label winText = new Label();
    private Button playAgainBtn = new Button("Play again");
    private Button exitButton = new Button("Exit");
    private boolean loading = false;
    private final Sound victorySound = new Sound(GameSounds.VICTORY);

    public VictoryScene() {
        winText.setText("YOU WIN!");
        winText.setTextFill(Color.WHITE);
        winText.setFont(Font.font(20));
        winText.setAlignment(Pos.TOP_CENTER);

        HBox buttons = new HBox(30);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.getChildren().addAll(playAgainBtn, exitButton);


        playAgainBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!loading) {
                    victorySound.stop();
                    GameScene.replayGame();
                    stage.setScene(GameScene.getScene());
                    loading = true;
                }
            }
        });
        playAgainBtn.setCursor(Cursor.HAND);

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                victorySound.stop();
                stage.close();
            }
        });
        exitButton.setCursor(Cursor.HAND);

        background.getChildren().addAll(winText, buttons);

        Sound.stopAll();
        victorySound.play();
    }

}
