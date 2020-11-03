import builder.GamesFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        System.out.println("Starting...");

        GamesFactory mi = new GamesFactory();
        mi.createGame(15,20, 30, 48, 3);
    }


}
