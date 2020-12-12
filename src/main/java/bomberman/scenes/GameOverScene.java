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

public class GameOverScene extends StaticScene {

    private Label gameOverText = new Label();
    private Label pointText = new Label();
    private Button playAgainBtn = new Button("Play again");
    private Button exitButton = new Button("Exit");
    private boolean loading = false;
    private final Sound loseSound = new Sound(GameSounds.GAME_OVER);

    public GameOverScene() {
        gameOverText.setText("YOU LOSE!");
        gameOverText.setTextFill(Color.WHITE);
        gameOverText.setFont(Font.font(20));
        gameOverText.setAlignment(Pos.TOP_CENTER);


        pointText.setText("SCORES: " + GameLoop.getPoint());
        pointText.setTextFill(Color.WHITE);
        pointText.setFont(Font.font(16));
        pointText.setAlignment(Pos.TOP_CENTER);

        HBox buttons = new HBox(30);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.getChildren().addAll(playAgainBtn, exitButton);


        playAgainBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!loading) {
                    loseSound.stop();
                    GameScene.replayGame();
                    stage.setScene(GameScene.getScene());
                    loading = true;
                }
            }
        });
        playAgainBtn.setCursor(Cursor.HAND);

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                loseSound.stop();
                stage.close();
            }
        });
        exitButton.setCursor(Cursor.HAND);

        background.getChildren().addAll(gameOverText, pointText, buttons);

        Sound.stopAll();
        loseSound.play();

    }
}
