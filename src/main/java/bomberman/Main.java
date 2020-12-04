package bomberman;

import bomberman.constants.GameConstants;
import bomberman.gui.Menu;
import bomberman.scenes.StartScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(GameConstants.GAME_NAME);

        //GameScene.startScene();
        Scene scene = StartScene.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }


}
