package view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import launcher.Launch;
import model.game.manager.FXControler;
import model.game.manager.Manager;

public class Game {

    Manager man = Launch.getManager();

    @FXML private BorderPane gameBp;
    @FXML private Button restartButton;
    @FXML private Button homeButton;
    @FXML private Text scoreText;

    @FXML
    public void initialize() {
        FXControler fxControler = new FXControler(gameBp,Launch.getStage());
        fxControler.initializeGame(restartButton,homeButton,scoreText);

//        TODO: trouver comment bien bind la valeur de gameOver
        man.gameOverProperty().addListener((ChangeListener<? super Boolean>) (observable, oldValue, newValue) -> {
            System.out.println("Yuuus");
            if(newValue) {
                Launch.getNavigator().navigateTo("ScoreBoard", Launch.getStage());
            }
        });
    }
}
