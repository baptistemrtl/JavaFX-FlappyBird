package model;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import model.game.World;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurSimple;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.creator.Creator;
import model.game.creator.CreatorRandom;
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
import javafx.scene.input.KeyCode;

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
    private Creator creator;
    private Log currentLog;
    private BirdDisplacer birdDeplaceur ;
    private Displacer obstacleDisplacer;
    //private Displacer obstacleDeplaceur = new ObstacleDisplacer();
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

    public void setScoreCourant(int scoreCourant) {
        this.scoreCourant.set(scoreCourant);
    }

    public Manager() {
        creator = new CreatorRandom();
        currentWorld = creator.createWorld();
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

    public void setCurrentBird(Bird bird) {
        currentWorld.replaceCurrentBird(bird);
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public List<Obstacle> getAllObstacles() {
        List<Obstacle> list = new ArrayList<>();
        ObservableMap<Position, Element> elements = currentWorld.getElements();

        for (Element obstacle : elements.values()) {
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
        boucleur.addListener(this);
        boucleur.setRunning(true);
        birdDeplaceur.setEnableMove(true);
        new Thread(boucleur).start();
    }

    public void stopBoucle() {
        boucleur.setRunning(false);
    }

    @Override
    public void invalidated(Observable observable) {
        ObservableMap<Position, Element> elements = currentWorld.getElements();
        if (compteurBoucl == 1){
            for (Map.Entry<Position,Element> entry : elements.entrySet()){
                if (entry.getValue() instanceof Obstacle){
                    if(!obstacleDisplacer.move((Obstacle) entry.getValue())){
                        birdDeplaceur.setEnableMove(false);
                        stopBoucle();
                    }

                    if (currentWorld.getFirstDownPipe().getPos().getX() < (currentWorld.getFirstDownPipe().getWidth())*-1){
                        currentWorld.delElement(currentWorld.getFirstUpPipe());
                        currentWorld.delElement(currentWorld.getFirstDownPipe());
                        currentWorld.addListElement(creator.createObstacle(currentWorld));
                        System.out.println("op");

                        //  /!\ Problème : il faut passer sur une ObservableList qui soit dans l'ordre de suppresion et d'ajout.
                    }

                }

            }
            birdDeplaceur.drop(getCurrentBird());
            compteurBoucl=0;
        }
        compteurCrea++;
        compteurBoucl++;
    }

    public void keyMove(KeyCode keyCode){
        if (keyCode == KeyCode.SPACE){
            if (!birdDeplaceur.move(currentBird)){
                birdDeplaceur.setEnableMove(false);
                stopBoucle();
            }
        }
    }
}
