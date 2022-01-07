package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Manager;

import java.util.Objects;

public class Launch extends Application {

    private  static Manager man;

    static {
        try {
            man = new Manager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Manager getManager() {
        return man;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}