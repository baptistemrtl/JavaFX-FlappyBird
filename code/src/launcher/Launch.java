package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.game.manager.Manager;
import model.game.manager.Navigator;

import java.io.IOException;
import java.util.Objects;

public class Launch extends Application {

    private static Stage stage;
    public static Stage getStage(){ return stage; }

    private static Manager man = new Manager();
    public static Manager getManager() { return man; }

    private static Navigator nav;
    static {
        try {
            nav = new Navigator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Navigator getNavigator() {
        return nav;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/flopflop.png"));
        nav.navigateTo("MainWindow", stage);
    }

    @Override
    public void stop() throws Exception {
        man.stopBoucle();
        super.stop();
    }
}
