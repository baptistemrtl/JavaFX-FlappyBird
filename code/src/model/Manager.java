package model;
import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import model.game.*;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurSimple;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.creator.Creator;
import model.game.creator.CreatorRandom;
import model.game.creator.CreatorSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;
import model.game.element.Bird;
import model.game.logs.Log;
import model.game.logs.LogSimple;

public class Manager implements InvalidationListener {

    private Player currentPlayer;
    private World currentWorld;
    private Bird currentBird;
    private Collider collider;
    private Boucleur boucleur;
    private CreatorSimple creator;
    private Log currentLog;
    private Displacer birdDeplaceur ;
    //private Displacer obstacleDeplaceur = new ObstacleDisplacer(); à modif car l'obstacle s'en fou de connaître un collider
    private LoaderBinaire loader;
    private SaverBinaire saver;

    private int compteurBoucl = 0;

    public IntegerProperty scoreCourant = new SimpleIntegerProperty();
    public IntegerProperty ScoreCourantProperty() {
        return scoreCourant;
    }
    public int getScoreCourant() {return scoreCourant.get();}
    public void setScoreCourant(int scoreCourant) {this.scoreCourant.set(scoreCourant); }


    public Manager(){
        currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        boucleur = new BoucleurSimple();
        creator = new CreatorSimple("rsrc/testFinishedWorlds/world1.txt");
        birdDeplaceur = new BirdDisplacer(collider);

    }

    public void creerMonde(){
        currentWorld = creator.readWorldFile();
        currentBird = currentWorld.getCurrentBird();
    }

    public World getCurrentWorld(){ return currentWorld; }

    public void dataLoad(){
        currentLog = new LogSimple((ObservableList<Player>) loader.loadData());
    }

    public void dataSave(){
        saver.saveData(currentLog.getPlayers());
    }

    public Log getLog(){
        return this.currentLog;
    }

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

        if (compteurBoucl == 150){
            birdDeplaceur.move(currentBird);
            compteurBoucl=0;
        }
        compteurBoucl++;
    }
}
