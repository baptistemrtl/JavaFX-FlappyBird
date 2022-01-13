package model.game.manager;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.Player;
import model.game.World.World;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;
import model.game.logs.Log;
import model.game.logs.LogSimple;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Manager {


    private BooleanProperty gameOver = new SimpleBooleanProperty();
        public Boolean getGameOver(){ return gameOver.get();}
        public void setGameOver(Boolean bool){ gameOver.set(bool);}
        public BooleanProperty gameOverProperty(){ return gameOver; }

    private Player currentPlayer;
    private World currentWorld;
    private Bird currentBird;
    private final Collider collider;
    private final Boucleur boucleur = new BoucleurObstacle();
    private Log currentLog;
    private final BirdDisplacer birdDeplaceur ;
    private final  Displacer obstacleDisplacer;

    private LoaderBinaire loader;
    private SaverBinaire saver;

    private int compteurBoucl = 0;
    private int compteurCrea = 0;

    public IntegerProperty scoreCourant = new SimpleIntegerProperty();
    public IntegerProperty ScoreCourantProperty() {
        return scoreCourant;
    }
    public int getScoreCourant() {
        return scoreCourant.get();
    }
    public void setScoreCourant(int scoreCourant) {this.scoreCourant.set(scoreCourant);}

    public Manager() {
        currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        currentBird = currentWorld.getCurrentBird();
        birdDeplaceur = new BirdDisplacer(collider);
        obstacleDisplacer = new ObstacleDisplacer(collider);
        currentLog = new LogSimple();
    }

    public Boolean isGameOver() {
        return gameOver.get();
    }

    //Initialisation

    public void createWorld(){
        currentWorld.restartWorld();
        collider.setWorld(currentWorld);
        currentBird = currentWorld.getCurrentBird();
    }

    public void setCurrentPlayer(String pseudo) {
        currentPlayer = currentLog.searchPlayer(pseudo);
        if (currentPlayer == null) {
            currentPlayer = new Player(pseudo);
            currentLog.addPlayer(currentPlayer);
           // saver.saveData(currentLog.getPlayers());
        }

        System.out.println(currentPlayer.getPseudo());
    }

    public Bird getCurrentBird() {
        return currentWorld.getCurrentBird();
    }


    public World getCurrentWorld() {
        return currentWorld;
    }

    public List<Obstacle> getAllObstacles() {
        List<Obstacle> list = new ArrayList<>();
        ObservableList<Element> elements = currentWorld.getElements();

        for (Element obstacle : elements) {
            if (obstacle instanceof Obstacle){
                list.add((Obstacle) obstacle);
            }
        }

        return list;
    }

    public Log getLog() {
        return this.currentLog;
    }

    //Persistance
    public void dataLoad() {
        currentLog = new LogSimple((ObservableList<Player>) loader.loadData());
    }

    public void dataSave() {
        saver.saveData(currentLog.getPlayers());
    }

    //Boucle
    public void startBoucle() {
        setGameOver(true);
        //boucleur.addListener(this);
        boucleur.setRunning(true);
        birdDeplaceur.setEnableMove(true);
        new Thread(boucleur).start();
    }

    public void stopBoucle() {
        boucleur.setRunning(false);
    }


    public void keyMove(KeyCode keyCode) {
        if (!getGameOver()){
            return;
        }
        if (keyCode == KeyCode.SPACE) {
            if (!birdDeplaceur.move(currentBird,15.0)) { //animation
                birdDeplaceur.setEnableMove(false);
                gameOver.set(false);
                stopBoucle();
            }
        }
    }
}
