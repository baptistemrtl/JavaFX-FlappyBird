package view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import launcher.Launch;
import model.game.manager.FXControler;
import model.game.manager.Manager;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Game {

    private Manager man = Launch.getManager();

    @FXML
    private BorderPane gameBp;
    @FXML
    private Button restartButton;
    @FXML
    private Button homeButton;

    private Stage stage;


    @FXML
    public void initialize() throws Exception {
        FXControler fxControler = new FXControler(gameBp,Launch.getStage());
        fxControler.initializeGame(restartButton,homeButton);
    }

}
