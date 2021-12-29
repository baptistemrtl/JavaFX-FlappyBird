package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Manager;
import model.Position;
import model.game.World;
import model.game.element.Bird;
import javafx.fxml.FXML;



public class MainWindow {

    private final Image gameTitle = new Image("image/flopflop.png");

    @FXML
    private ImageView flopflop;

    @FXML
    private Button playButton;

    @FXML
    private TextField pseudoJoueur;



    @FXML
    public void initialize(){
        flopflop = new ImageView(gameTitle);
    }

    private void lancerPartie() throws Exception {
        Manager man = new Manager();

        Stage primaryStage = new Stage();
        Parent racine = FXMLLoader.load(getClass().getResource("/FXML/Game.fxml"));
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
