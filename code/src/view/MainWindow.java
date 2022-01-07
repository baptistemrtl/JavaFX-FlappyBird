package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import model.Manager;


public class MainWindow {

    private final Image gameTitle = new Image("image/flopflop.png");

    @FXML
    private ImageView flopflop;

    @FXML
    public void initialize(){
        flopflop = new ImageView(gameTitle);
    }

    private void lancerPartie() throws Exception {
        Manager man = new Manager();

        Stage primaryStage = new Stage();
        Parent racine = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Game.fxml")));
        Scene scene = new Scene(racine);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        actionEvent.consume();
        lancerPartie();
    }
}
