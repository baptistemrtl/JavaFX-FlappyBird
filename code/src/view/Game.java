package view;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import launcher.Launch;
import model.game.manager.FXControler;

/**
 * Controller pour la vue du jeu
 */
public class Game {

    @FXML private BorderPane gameBp;
    @FXML private Button restartButton;
    @FXML private Button homeButton;
    @FXML private Text scoreText;

    /**
     * Méthode à l'instanciation de la Scene qui va permettre de préparer la vue au lancement d'une partie
     */
    @FXML
    public void initialize() {
        FXControler fxControler = new FXControler(gameBp,Launch.getStage());
        fxControler.initializeGame(restartButton,homeButton,scoreText);
    }
}
