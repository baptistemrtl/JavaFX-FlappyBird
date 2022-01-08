package model;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.game.World;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurSimple;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.creator.CreatorSimple;
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
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manager implements InvalidationListener {

    private Boolean gameOver;
    private Boolean isMoving;

    private Player currentPlayer;
    private World currentWorld = new World();
    private Bird currentBird;
    private Collider collider;
    private Boucleur boucleur = new BoucleurSimple();
    private CreatorSimple creator;
    private Log currentLog;
    private BirdDisplacer birdDeplaceur ;
    private Displacer obstacleDisplacer;
    //private Displacer obstacleDeplaceur = new ObstacleDisplacer();
    private LoaderBinaire loader;
    private SaverBinaire saver;

    private int compteurBoucl = 0;

    public IntegerProperty scoreCourant = new SimpleIntegerProperty();
    public IntegerProperty ScoreCourantProperty() {
        return scoreCourant;
    }
    public int getScoreCourant() {return scoreCourant.get();}
    public void setScoreCourant(int scoreCourant) {this.scoreCourant.set(scoreCourant); }


    public Manager() {
        creator = new CreatorSimple("rsrc/testFinishedWorlds/world1.txt");
        currentWorld = creator.readWorldFile();
        collider = new ColliderSimple(currentWorld);
        currentBird = currentWorld.getCurrentBird();
        birdDeplaceur = new BirdDisplacer(collider);
        obstacleDisplacer = new ObstacleDisplacer(collider);
        currentLog = new LogSimple();

    }

    public Boolean isGameOver() {
        return gameOver;
    }

    //Initialisation

    public void setCurrentPlayer(String pseudo){
        currentPlayer = currentLog.searchPlayer(pseudo);
        if (currentPlayer == null){
            currentPlayer = new Player(pseudo);
            currentLog.addPlayer(currentPlayer);
           // saver.saveData(currentLog.getPlayers());
        }
        System.out.println(currentPlayer.getPseudo());
    }


    public Bird getCurrentBird() {
        return currentWorld.getCurrentBird();
    }

    public void setCurrentBird(Bird bird){
        currentWorld.replaceCurrentBird(bird);
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public List<Obstacle> getAllObstacles() {
        List<Obstacle> list = new ArrayList<>();
        ObservableMap<Position, Element> elements = currentWorld.getElements();

        for (Element obstacle : elements.values()){
            if (obstacle instanceof Obstacle){
                list.add((Obstacle) obstacle);
            }
        }

        return list;
    }

    public Log getLog(){
        return this.currentLog;
    }

    //Persistance
    public void dataLoad(){
        currentLog = new LogSimple((ObservableList<Player>) loader.loadData());
    }

    public void dataSave(){
        saver.saveData(currentLog.getPlayers());
    }

    //Boucle
    public void startBoucle() {
        boucleur.addListener(this);
        boucleur.setRunning(true);
        new Thread(boucleur).start();
    }

    public void stopBoucle(){
        boucleur.setRunning(false);
    }

    @Override
    public void invalidated(Observable observable) {
        ObservableMap<Position, Element> elements = currentWorld.getElements();
        if (compteurBoucl == 1){
            for (Map.Entry<Position,Element> entry : elements.entrySet()){
                if (entry.getValue() instanceof Obstacle){
                    obstacleDisplacer.move((Obstacle) entry.getValue());
                }

            }
            birdDeplaceur.drop(getCurrentBird());
            compteurBoucl=0;
        }
        compteurBoucl++;
    }

    public void keyMove(KeyCode keyCode){
        if (!birdDeplaceur.move(currentBird)){
            stopBoucle();
        }
    }
}
