/**
 * Main
 */
import javafx.application.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.Scene;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Stage window;

    Scene signin,dashboard;
    int stage;
    @Override
    public void start(Stage primaryStage) throws Exception, FileNotFoundException, IOException, ClassNotFoundException {

        window = primaryStage;
        Loginpage login = new Loginpage(window);
        signin = new Scene(login,400,400);
        window.setScene(signin);

        window.show();
    }
}