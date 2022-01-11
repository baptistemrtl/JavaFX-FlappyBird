package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import launcher.Launch;
import model.game.manager.Manager;
import model.Position;
import model.game.element.Background;
import model.game.manager.Navigator;


public class MainWindow {

    private final Image gameTitle = new Image("image/flopflop.png");
    private final Manager man = Launch.getManager();

    @FXML
    private BorderPane mainBp;
    @FXML
    private Button startButton;
    @FXML
    private TextField pseudoJoueur;
    @FXML
    private Button scoreboardButton;

    @FXML
    public void initialize(){

        Background bg = new Background(450,700,new Position(0,0),"image/background2.png");
        ImageView background = new ImageView(bg.getImage());
        background.setFitHeight(bg.getHeight());
        background.setFitWidth(bg.getWidth());
        background.setX(bg.getPos().getX());
        background.setY(bg.getPos().getY());
        mainBp.getChildren().add(0,background);

        ImageView flopflop = new ImageView(new Image("image/flopflop.png"));
        flopflop.setFitHeight(150);
        flopflop.setFitWidth(300);
        flopflop.setX(80);
        flopflop.setY(0);
        mainBp.getChildren().add(1,flopflop);

    }

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        actionEvent.consume();
        Launch.getNavigator().navigateTo("Game",(Stage) startButton.getScene().getWindow());
    }


    @FXML
    public void openScoreboard(ActionEvent actionEvent) throws IOException {
        actionEvent.consume();
        Launch.getNavigator().navigateTo("ScoreBoard",(Stage) startButton.getScene().getWindow());
    }
}
