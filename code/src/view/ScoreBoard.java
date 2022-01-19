package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import launcher.Launch;
import model.game.element.Background;
import model.game.element.Position;
import model.game.manager.FXControler;

import java.io.IOException;
import java.util.Objects;

public class ScoreBoard {

    private final Image firstmedal = new Image("image/firstmedal.png");
    private final Image secondmedal = new Image("image/secondmedal.png");
    private final Image thirdmedal = new Image("image/thirdmedal.png");

    @FXML private ImageView first;
    @FXML private ImageView second;
    @FXML private ImageView third;
    @FXML private BorderPane scoreBp;

    @FXML Text firstName;
    @FXML Text secondName;
    @FXML Text thirdName;

    @FXML Text scoreFirst;
    @FXML Text scoreSecond;
    @FXML Text scoreThird;


    /**
     * Méthode à l'instanciation de la Scene de scoreBoard
     */
    @FXML
    public void initialize() {
        //Instanciation et ajout des éléments de base de cette vue
         Background bg = new Background(450,700,new Position(0,0),"image/background2.png");
         ImageView background = new ImageView(bg.getImage());
         background.setFitHeight(bg.getHeight());
         background.setFitWidth(bg.getWidth());
         background.setX(bg.getPos().getX());
         background.setY(bg.getPos().getY());
         scoreBp.getChildren().add(0,background);
         first.setImage(firstmedal);
         second.setImage(secondmedal);
         third.setImage(thirdmedal);

         //Gestion du binding du top 3
        FXControler fxControler = new FXControler(scoreBp,Launch.getStage());
        fxControler.initializeScoreBoard(firstName,secondName,thirdName,scoreFirst,scoreSecond,scoreThird);
    }

    /**
     * Redirection vers la MainWindow
     * @param actionEvent
     */
    @FXML
    public void retourButtonAction(ActionEvent actionEvent) {
        actionEvent.consume();
        Launch.getNavigator().navigateTo("MainWindow", Launch.getStage());
    }
}
