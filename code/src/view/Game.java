package view;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import launcher.Launch;
import model.Manager;
import model.Position;
import model.game.World;
import model.game.element.Background;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


public class Game {

    private Manager man = Launch.getManager();

    @FXML
    private BorderPane gameBp;

    @FXML
    private ImageView gameIv;

    @FXML
    public void initialize() throws Exception{
        gameIv.setFitWidth(800);
        gameIv.setFitHeight(600);
        World world = man.getCurrentWorld();
        Bird currentBird = man.getCurrentBird();

        Background bg = new Background(450,700,new Position(0,0),"image/background2.png");
        ImageView background = new ImageView(bg.getImage());
        background.setFitHeight(bg.getHeight());
        background.setFitWidth(bg.getWidth());
        background.setX(bg.getPos().getX());
        background.setY(bg.getPos().getY());
        gameBp.getChildren().add(0,background);

        man.startBoucle();
        /*TextField scoreTF = new TextField();
        scoreTF.setLayoutX(175);
        scoreTF.setLayoutY(0);
        scoreTF.setMinWidth(100);
        scoreTF.setMinHeight(200);
        scoreTF.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        scoreTF.textProperty().bind(man.scoreCourant.asString());
        gameBp.getChildren().add(2,scoreTF);*/


        if (currentBird != null){
            ImageView bird = new ImageView(new Image(currentBird.getImage()));
            bird.setFitHeight(currentBird.getHeight());
            bird.setFitWidth(currentBird.getWidth());
            bird.setX(currentBird.getPos().getX());
            bird.setY(currentBird.getPos().getY());
        }

        Collection<Element> elements = world.getElements().values();
        for (Element obs : elements){
            if(obs instanceof Obstacle){
                ImageView obstacle = new ImageView(new Image(obs.getImage()));
                obstacle.setFitWidth(obs.getWidth());
                obstacle.setFitHeight(obs.getHeight());
                obstacle.setX(obs.getPos().getX());
                obstacle.setY(obs.getPos().getY());
            }
        }

        /*Launch.getManager().getCurrentWorld().getValues().addListener((MapChangeListener.Change<? extends Position,? extends Element> change)-> {
            System.out.println("change");

            //Ajout
            ObservableMap<? extends Position, ? extends Element> add = (ObservableMap<? extends Position, ? extends Element>) change.getValueAdded();
            for (Map.Entry<? extends Position, ? extends Element> entry : add.entrySet()){
                System.out.println("add");
                update(entry.getValue());
            }

            //Modification
            ObservableMap<? extends Position, ? extends Element> map = change.getMap();
            for (Map.Entry<? extends Position, ? extends Element> entry : map.entrySet()){
                update(entry.getValue());
            }

            //Suppression
            ObservableMap<? extends Position, ? extends Element> del = (ObservableMap<? extends Position, ? extends Element>) change.getValueRemoved();
            for (Map.Entry<? extends Position, ? extends Element> entry : map.entrySet()){
                System.out.println("del");
                Iterator<Node> iterator = gameBp.getChildren().iterator();
                while(iterator.hasNext()){
                    Node leNode = iterator.next();
                    if (leNode.getUserData() == entry.getValue()){
                        iterator.remove();
                    }
                }
            }

        });*/

        Launch.getManager().getCurrentWorld().getValuesList().addListener((ListChangeListener.Change<? extends Element> change)-> {
            while (change.next()) {
                for (Element elm : change.getAddedSubList()) {
                    update(elm);
                }
            }
            for (Element elm : change.getRemoved()) {
                gameBp.getChildren().removeIf(leNode -> leNode.getUserData() == elm);
            }
        });

        for (Element obs : elements) {
            update(obs);
        }

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

        elementIv.layoutXProperty().bind(element.getPos().xProperty());
        elementIv.layoutYProperty().bind(element.getPos().yProperty());
    }

    @FXML
    public void move(KeyEvent event){
        System.out.println("move");
           // Launch.getManager().keyMove(event.getCode());
    }
}
