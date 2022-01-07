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

    private Player currentPlayer;
    private World currentWorld;
    private Bird currentBird;
    private Collider collider;
    private Boucleur boucleur;
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
        currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        boucleur = new BoucleurSimple();
        creator = new CreatorSimple("rsrc/testFinishedWorlds/world1.txt");
        birdDeplaceur = new BirdDisplacer(collider);
        obstacleDisplacer = new ObstacleDisplacer(null);

    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void creerMonde() {
        currentWorld = creator.readWorldFile();
        collider.setWorld(currentWorld);
        currentBird = currentWorld.getCurrentBird();

        System.out.println(getCurrentBird().getImage());
    }

    public Bird getCurrentBird() {
        return currentWorld.getCurrentBird();
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
//            birdDeplaceur.drop(getCurrentBird());
            compteurBoucl=0;
        }
        compteurBoucl++;
    }

    public void keyMove(KeyCode keyCode){
        System.out.println("keyMMove");
        birdDeplaceur.move(currentWorld.getCurrentBird());
        System.out.println(currentWorld.getCurrentBird().getImage()); // Exception current Bird Null
    }
}
