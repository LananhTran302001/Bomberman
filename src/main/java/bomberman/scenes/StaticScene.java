package bomberman.scenes;

import bomberman.constants.GameConstants;
import bomberman.constants.GameSounds;
import bomberman.gui.Sound;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class StaticScene {

    static Stage stage;

    private Scene scene;
    private Group root;

    VBox background = new VBox(50);

    public StaticScene() {
        root = new Group();
        scene = new Scene(root, GameConstants.SCENE_WIDTH, GameConstants.SCENE_HEIGHT);
        root.getChildren().addAll(background);

        background.setPrefWidth(GameConstants.SCENE_WIDTH);
        background.setPrefHeight(GameConstants.SCENE_HEIGHT);
        background.setStyle("-fx-background-color: #111111");
        background.setAlignment(Pos.CENTER);

    }

    public static void setStage(Stage s) {
        stage = s;
    }

    public void show() {
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public VBox getContainer() {
        return background;
    }

}
