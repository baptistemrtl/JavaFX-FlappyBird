package view;


import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import launcher.Launch;
import model.Manager;
import model.Position;
import model.game.World;
import model.game.element.Background;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.Map;


public class Game {


    @FXML
    private BorderPane gameBp;

    @FXML
    private ImageView gameIv;

    @FXML
    public void initialize() throws Exception{
        gameIv.setFitWidth(800);
        gameIv.setFitHeight(600);
        Manager man = Launch.getManager();
        man.creerMonde();
        World world = man.getCurrentWorld();
        Bird currentBird = world.getCurrentBird();
        Background bg = new Background(900,900,new Position(0,0),"image/background2.png");

        ImageView background = new ImageView(bg.getImage());
        background.setFitHeight(bg.getHeight());
        background.setFitWidth(bg.getWidth());
        background.setX(bg.getPos().getX());
        background.setY(bg.getPos().getY());
        gameBp.getChildren().add(background);

        if (currentBird != null){
            ImageView bird = new ImageView(new Image(currentBird.getImage()));
            bird.setFitHeight(currentBird.getHeight());
            bird.setFitWidth(currentBird.getWidth());
            bird.setX(currentBird.getPos().getX());
            bird.setY(currentBird.getPos().getY());
            gameBp.getChildren().add(bird);
        }


        ObservableMap<Position, Element> elements = world.getElements();
        if (elements != null){
            for (Map.Entry<Position,Element> entry : elements.entrySet()){
                Element obs = entry.getValue();
                ImageView obstacle = new ImageView(new Image(obs.getImage()));
                obstacle.setFitWidth(obs.getWidth());
                obstacle.setFitHeight(obs.getHeight());
                obstacle.setX(obs.getPos().getX());
                obstacle.setY(obs.getPos().getY());
                gameBp.getChildren().add(obstacle);
            }
        }
        man.startBoucle();
        for (Map.Entry<Position,Element> entry : elements.entrySet()){
            update(entry.getValue());
        }
    }

    public void update(Element element) {
        Manager man = Launch.getManager();
        ImageView elementIv = new ImageView();
        elementIv.setImage(new Image(element.getImage()));
        if (element instanceof Bird){
            elementIv.setFitWidth(man.getCurrentBird().getWidth());
            elementIv.setFitHeight(man.getCurrentBird().getHeight());
        }
        if (element instanceof Obstacle){
            elementIv.setFitWidth(element.getWidth());
            elementIv.setFitHeight(element.getHeight());
        }
        gameBp.getChildren().add(elementIv);
        elementIv.layoutXProperty().bind(element.getPos().xProperty());
        elementIv.layoutYProperty().bind(element.getPos().yProperty());
    }

}
