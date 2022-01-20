package launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.game.manager.Manager;
import model.game.manager.Navigator;

import java.io.IOException;

/**
 * Le launcher du jeu.
 */
public class Launch extends Application {


    private static Stage stage;

    /**
     * Get lestage.
     *
     * @return le stage
     */
    public static Stage getStage(){ return stage; }

    private static final Manager man = new Manager();

    /**
     * Get le manager.
     *
     * @return le manager
     */
    public static Manager getManager() { return man; }

    private static Navigator nav;

    /**
     * Get le navigator.
     *
     * @return le navigator
     */
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

    /**
     * Arrête l'application.
     */
    @Override
    public void stop() throws Exception {
        man.stopBoucle();
        super.stop();
    }
}
