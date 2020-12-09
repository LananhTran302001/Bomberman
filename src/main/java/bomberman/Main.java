package bomberman;

import bomberman.constants.GameConstants;
import bomberman.scenes.StartScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(GameConstants.GAME_NAME);

        //GameScene.startScene();
        StartScene.setStage(primaryStage);
        Scene scene = new StartScene().getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }


}
