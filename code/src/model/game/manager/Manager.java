package model.game.manager;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.StringConverter;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

import launcher.Launch;
import model.Player;
import model.game.World.World;
import model.game.animation.Animation;
import model.game.animation.AnimationBird;
import model.game.animation.AnimationObstacle;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
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
import model.game.renderer.ISound;
import model.game.renderer.SoundPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private BooleanProperty gameOver = new SimpleBooleanProperty();
    public Boolean isGameOver(){ return gameOver.get();}
    public void setGameOver(Boolean bool){ gameOver.set(bool);}
    public BooleanProperty gameOverProperty(){ return gameOver; }



    private Player currentPlayer;
    private World currentWorld;
    private Bird currentBird;
    private Collider collider;
    private Boucleur boucleur = new BoucleurObstacle();
    private Boucleur birdBoucleur = new BoucleurBird();
    private Log currentLog = new LogSimple();
    private BirdDisplacer birdDeplaceur;
    private Displacer obstacleDisplacer;
    private Animation animationObs;
    private AnimationBird animationBird;
    private ScoreChecker scoreChecker;
    private Thread threadScore;
    private SoundPlayer soundPlayer;

    private LoaderBinaire loader;
    private SaverBinaire saver;


    public StringProperty stringScore = new SimpleStringProperty();

    public StringProperty stringScoreProperty(){ return stringScore; }
    public int getStringScore(){ return Integer.parseInt(stringScore.get()); }

    public Manager() {
        currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        currentBird = currentWorld.getCurrentBird();
        birdDeplaceur = new BirdDisplacer(collider);
        obstacleDisplacer = new ObstacleDisplacer(collider);
        animationObs = new AnimationObstacle((ObstacleDisplacer) obstacleDisplacer,collider,(BoucleurObstacle) boucleur);
        animationBird = new AnimationBird(birdDeplaceur,collider,(BoucleurBird) birdBoucleur,new BoucleurDrop());
        loader = new LoaderBinaire("save.bin");
        saver = new SaverBinaire("save.bin");
        soundPlayer = new SoundPlayer("rsrc/sound/resources_sounds_swoosh.wav");
        dataLoad();
    }

    //Getters
    public Bird getCurrentBird() {
        return collider.getWorld().getCurrentBird();
    }
    public Log getLog() {
        return this.currentLog;
    }
    public World getCurrentWorld() {
        return collider.getWorld();
    }

    //Setter
    public void setCurrentPlayer(String pseudo) {
        currentPlayer = currentLog.searchPlayer(pseudo);
        if (currentPlayer == null) {
            currentPlayer = new Player(pseudo);
            currentLog.addPlayer(currentPlayer);
            dataSave();
        }
        System.out.println(currentPlayer.getPseudo());
    }

    //Initialisation

    public void createWorld(){
        collider.getWorld().restartWorld();
        currentBird = collider.getWorld().getCurrentBird();
        animationObs.setCollider(collider);
        animationBird.setCollider(collider);
        scoreChecker = new ScoreChecker(collider.getWorld());
        stringScore.bindBidirectional(scoreChecker.scoreCourantProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        });
    }


    //Persistance
    public void dataLoad() {
        currentLog = new LogSimple((ObservableList<Player>) loader.loadData());
        currentLog.playersProperty().addListener((ListChangeListener<? super Player>) change ->{
            while (change.next()){
                for (Player pl : change.getAddedSubList()){
                    currentLog.sort(currentLog.playersProperty().get());
                    break;
                }
            }
            dataSave();
        });
    }

    public void dataSave() {
        saver.saveData(currentLog.getPlayers());
    }

    //Boucle
    public void startBoucle() { // = startGame
        soundPlayer.setFilePath("rsrc/sound/resources_sounds_swoosh.wav");
        soundPlayer.play();
        setGameOver(false);
        animationObs.animate();
        animationBird.initalizeAnimation();
        scoreChecker.setRunning(true);
        scoreChecker.setScoreCourant(0);
        threadScore = new Thread(scoreChecker);
        threadScore.start();
        animationBird.propertyStop().addListener((ChangeListener<? super Boolean>)(observable, oldValue, newValue) -> {
            if(newValue){
                setGameOver(newValue);
                soundPlayer.setFilePath("rsrc/sound/resources_sounds_hit.wav");
                soundPlayer.play();
            }
        });
    }

    public void stopBoucle() { // = end OF A PARTY
        if (currentPlayer != null && getStringScore() > currentPlayer.getScoreMax()) {
            currentPlayer.setScoreMax(getStringScore());
            dataSave();
        }
        if (threadScore != null) {
            threadScore.interrupt();
        }

        animationBird.stopAll();
        scoreChecker.setRunning(false);
        animationObs.stopAnimation();
    }

    public void restartGame() {
        createWorld();
    }

    public void keyMove(KeyCode keyCode) {
        if (isGameOver()) {
            return;
        }

        if (keyCode == KeyCode.SPACE) {
            animationBird.animate();
            soundPlayer.setFilePath("rsrc/sound/resources_sounds_wing.wav");
            soundPlayer.play();
            if (animationBird.getThreadFly().isInterrupted() && animationBird.getThreadDrop().isInterrupted()) {
                gameOver.set(true);
            }
        }
    }


}
