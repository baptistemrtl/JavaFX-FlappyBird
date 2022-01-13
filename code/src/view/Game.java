package view;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import launcher.Launch;
import model.game.manager.FXControler;

public class Game {

    @FXML private BorderPane gameBp;
    @FXML private Button restartButton;
    @FXML private Button homeButton;

    @FXML
    public void initialize() {
        FXControler fxControler = new FXControler(gameBp,Launch.getStage());
        fxControler.initializeGame(restartButton,homeButton);
    }

}
