package bomberman;

import bomberman.builder.GamesFactory;
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
        System.out.println("Starting...");

        GamesFactory mi = new GamesFactory();
        mi.createGame(15,20, 30, 48, 3);
        launch(args);
    }


}
