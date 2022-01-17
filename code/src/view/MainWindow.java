package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import launcher.Launch;
import model.game.manager.Manager;
import model.game.element.Position;
import model.game.element.Background;


public class MainWindow {

    private final Manager man = Launch.getManager();

    @FXML private TextField pseudoJoueur;
    @FXML private BorderPane gameBp;

    @FXML
    public void initialize() {
        Background bg = new Background(450,700,new Position(0,0),"image/background2.png");
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

    @FXML
    public void startGame(ActionEvent actionEvent) {
        actionEvent.consume();
        if (pseudoJoueur.getText().isEmpty() || pseudoJoueur.getText().isBlank()) {
            Stage dialog = new Stage();
            dialog.initOwner(Launch.getStage());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.getIcons().add(new Image("image/dialog-error-icon.png"));
            Launch.getNavigator().navigateTo("Error", dialog);

            return;
        }
        Launch.getManager().setCurrentPlayer(pseudoJoueur.getText());
        Launch.getNavigator().navigateTo("Game", Launch.getStage());
        Launch.getNavigator().getOnUseScene().setOnKeyPressed(keyEvent -> man.keyMove(keyEvent.getCode()));
        Launch.getManager().startBoucle();
    }

    @FXML
    public void openScoreboard(ActionEvent actionEvent) {
        actionEvent.consume();
        Launch.getNavigator().navigateTo("ScoreBoard", Launch.getStage());
    }

    @FXML
    public void exitGame(ActionEvent actionEvent) {
        actionEvent.consume();
        Launch.getStage().close();
    }
}
