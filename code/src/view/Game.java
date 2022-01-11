package view;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import launcher.Launch;
import model.game.manager.FXControler;
import model.game.manager.Manager;
import model.Position;
import model.game.World.World;
import model.game.element.Background;
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


    public void update(Element element) {
        ImageView elementIv = new ImageView();
        elementIv.setImage(new Image(element.getImage()));
        if (element instanceof Bird) {
            elementIv.setFitWidth(Launch.getManager().getCurrentBird().getWidth());
            elementIv.setFitHeight(Launch.getManager().getCurrentBird().getHeight());
            gameBp.getChildren().add(2,elementIv);
        }

        if (element instanceof Obstacle) {
            elementIv.setFitWidth(element.getWidth());
            elementIv.setFitHeight(element.getHeight());
            gameBp.getChildren().add(1,elementIv);
        }

        elementIv.layoutXProperty().bindBidirectional(element.getPos().xProperty());
        elementIv.layoutYProperty().bindBidirectional(element.getPos().yProperty());
    }

    @FXML
    public void move(KeyEvent event){
        System.out.println("move");
           // Launch.getManager().keyMove(event.getCode());
    }
}
