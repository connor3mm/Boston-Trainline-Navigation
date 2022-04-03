import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Boston Metro System");
        primaryStage.setScene(new Scene(root, 405, 755));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}