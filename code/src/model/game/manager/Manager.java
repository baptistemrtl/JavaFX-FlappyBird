package model.game.manager;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import model.Player;
import model.game.World.World;
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

    public void createWorld(){
        currentWorld = creator.createWorld();
        collider = new ColliderSimple(currentWorld);
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
        gameOver = true;
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
        if (compteurBoucl == 1){
            for (Element element : getCurrentWorld().getElements()){
                if (element instanceof Obstacle){
                    if(!obstacleDisplacer.move(element)){
                        birdDeplaceur.setEnableMove(false);
                        stopBoucle();
                        gameOver = false;
                    }
                    if (element.getPos().getX() < -200){
                        System.out.println("<0");
                        creator.createObstacle(currentWorld);
                        currentWorld.delElement(element);
                        return;
                    }
                }
            }
            /*
            if (currentWorld.getFirstDownPipe().getPos().getX() < 0){
                currentWorld.getElements().remove(down.getPos());
                currentWorld.getElements().remove(up.getPos());
                currentWorld.addListElement(creator.createObstacle(currentWorld));

                //  /!\ Problème : il faut passer sur une ObservableList qui soit dans l'ordre de suppresion et d'ajout.
            }*/

            /*if (compteurCrea%20 == 0){
                creator.createObstacle(currentWorld);
            }*/

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
                gameOver = false;
                stopBoucle();
            }
        }
    }
}
