package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import launcher.Launch;
import model.game.manager.Manager;
import model.game.element.Position;
import model.game.element.Background;

/**
 * Controller pour la fenêtre principale
 */
public class MainWindow {

    private final Manager man = Launch.getManager();

    @FXML private TextField pseudoJoueur;
    @FXML private BorderPane gameBp;

    /**
     * Initialise la fenêtre principale
     */
    @FXML
    public void initialize() {
        Background bg = new Background(450,700,new Position(0,0), "image/background.png");
        ImageView background = new ImageView(bg.getImage());
        background.setFitHeight(bg.getHeight());
        background.setFitWidth(bg.getWidth());
        background.setX(bg.getPos().getX());
        background.setY(bg.getPos().getY());
        gameBp.getChildren().add(0,background);

        ImageView flopflop = new ImageView(new Image("image/flopflop.png"));
        flopflop.setFitHeight(150);
        flopflop.setFitWidth(300);
        flopflop.setX(80);
        flopflop.setY(0);
        gameBp.getChildren().add(1,flopflop);
    }

    /**
     * Redirection sur la fenêtre de création de partie
     *
     * @param actionEvent l'action event (ignoré)
     */
    @FXML
    public void startGame(ActionEvent actionEvent) {
        actionEvent.consume();
        //On regarde la validité du pseudo rentré
        if (pseudoJoueur.getText().isEmpty() || pseudoJoueur.getText().isBlank()) {
            //Affichage d'une fenêtre d'erreur si le pseudo est mal renseigné
            Stage dialog = new Stage();
            dialog.initOwner(Launch.getStage());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.getIcons().add(new Image("image/dialog-error-icon.png"));
            Launch.getNavigator().navigateTo("Error", dialog);

            return;
        }

        Launch.getManager().setCurrentPlayer(pseudoJoueur.getText()); //Instanciation du joueur actuel
        Launch.getNavigator().navigateTo("Game", Launch.getStage()); //Navigation sur la scene de Jeu
        Launch.getNavigator().getOnUseScene().setOnKeyPressed(keyEvent -> man.keyMove(keyEvent.getCode()));
        Launch.getManager().startBoucle(); //Lancement du jeu
    }

    /**
     * Redirection sur la fenêtre de scoreboard
     *
     * @param actionEvent l'action event (ignoré)
     */
    @FXML
    public void openScoreboard(ActionEvent actionEvent) {
        actionEvent.consume();
        Launch.getNavigator().navigateTo("ScoreBoard", Launch.getStage());
    }

    /**
     * Fermeture de l'application
     *
     * @param actionEvent l'action event (ignoré)
     */
    @FXML
    public void exitGame(ActionEvent actionEvent) {
        actionEvent.consume();
        Launch.getStage().close();
    }
}
