package launcher;

import model.game.manager.Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    public static Navigator getNavigator() { return nav; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")));
        //Scene scene = new Scene(parent);
        //primaryStage.setScene(scene);
        //primaryStage.show();

        Scene scene = nav.getOnUseScene();
        stage = primaryStage;
        nav.navigateTo("MainWindow",primaryStage);


    }

    @Override
    public void stop() throws Exception{
        man.stopBoucle();
        super.stop();
    }
}
