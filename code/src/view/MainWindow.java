package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button startButton;

    @FXML
    public void initialize(){
        flopflop = new ImageView(gameTitle);
    }

    private void lancerPartie() throws Exception {
        Manager man = new Manager();

        Stage stage=(Stage) startButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/FXML/Game.fxml")));
       // scene.getStylesheets().add(getClass().getResource("/CSS/background.css").toExternalForm());
        scene.setOnKeyPressed(keyEvent -> man.keyMove(keyEvent.getCode()));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        actionEvent.consume();
        lancerPartie();
    }
}
