import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

public class Launch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
