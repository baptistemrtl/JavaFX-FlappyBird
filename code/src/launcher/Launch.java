package launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.game.manager.Manager;
import model.game.manager.Navigator;

import java.io.IOException;

public class Launch extends Application {

    private static Stage stage;
    public static Stage getStage(){ return stage; }

    private static Manager man = new Manager();
    public static Manager getManager() { return man; }

    private static Navigator nav;
    public static Navigator getNavigator() {
        return nav;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setResizable(false);
        nav = new Navigator();
        stage.getIcons().add(new Image("/image/desktopicon.jpg"));
        nav.navigateTo("MainWindow", stage);
    }

    @Override
    public void stop() throws Exception {
        man.stopBoucle();
        super.stop();
    }
}
