package bomberman;

import bomberman.constants.GameConstants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import bomberman.scenes.EasyLevel;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(GameConstants.GAME_NAME);
        EasyLevel.setUpScene();
        Scene scene = EasyLevel.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }
}
