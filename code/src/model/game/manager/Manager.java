package model.game.manager;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.util.StringConverter;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

import model.Player;
import model.game.World.World;
import model.game.animation.Animation;
import model.game.animation.AnimationBird;
import model.game.animation.AnimationObstacle;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.logs.Log;
import model.game.logs.LogSimple;
import model.game.renderer.SoundPlayer;

/**
 * Manager est la classe qui gère le fonctionnement du jeu.
 */
public class Manager {

    private Player currentPlayer;
    private Log currentLog = new LogSimple();
    private ScoreChecker scoreChecker;
    private Thread threadScore;

    private final Collider collider;
    private final Animation animationObs;
    private final AnimationBird animationBird;
    private final SoundPlayer soundPlayer;

    private final LoaderBinaire loader;
    private final SaverBinaire saver;

    /**
     * Get le log
     *
     * @return le log
     */
    public Log getLog() {
        return this.currentLog;
    }

    /**
     * Get le monde actuel
     *
     * @return le monde actuel
     */
    public World getCurrentWorld() {
        return collider.getWorld();
    }

    /**
     * Set le joueur actuel.
     *
     * @param pseudo son pseudo
     */
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


    private final BooleanProperty gameOver = new SimpleBooleanProperty();

    /**
     * Getter pour le game over.
     *
     * @return le boolean
     */
    public Boolean isGameOver() { return gameOver.get(); }

    /**
     * Set game over.
     *
     * @param bool la nouvelle valeur
     */
    public void setGameOver(Boolean bool) { gameOver.set(bool); }

    /**
     * La BooleanProperty gameOver.
     *
     * @return La BooleanProperty
     */
    public BooleanProperty gameOverProperty() { return gameOver; }

    /**
     * The String score.
     */
    public StringProperty stringScore = new SimpleStringProperty();

    /**
     * Get le score
     *
     * @return le score (cast to int)
     */
    public int getStringScore() { return Integer.parseInt(stringScore.get()); }

    /**
     * Set le score
     *
     * @param score le nouveau score
     */
    public void setStringScore(int score) { stringScore.set(Integer.toString(score)); }

    /**
     * La StringProperty du score
     *
     * @return La StringProperty
     */
    public StringProperty stringScoreProperty() { return stringScore; }


    /**
     * Constructeur de Manager.
     */
    public Manager() {
        World currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        BirdDisplacer birdDeplaceur = new BirdDisplacer(collider);
        ObstacleDisplacer obstacleDisplacer = new ObstacleDisplacer(collider);
        BoucleurObstacle boucleur = new BoucleurObstacle();
        animationObs = new AnimationObstacle(obstacleDisplacer,collider, boucleur);
        BoucleurBird birdBoucleur = new BoucleurBird();
        animationBird = new AnimationBird(birdDeplaceur,collider, birdBoucleur,new BoucleurDrop());
        loader = new LoaderBinaire("save.bin");
        saver = new SaverBinaire("save.bin");
        soundPlayer = new SoundPlayer("rsrc/sound/resources_sounds_swoosh.wav");
        dataLoad();
    }

    //Initialisation

    /**
     * Crée le monde
     */
    public void createWorld(){
        collider.getWorld().restartWorld();
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


    /**
     * Charge les données
     */
//Persistance
    public void dataLoad() {
        currentLog = new LogSimple((ObservableList<Player>) loader.loadData());
    }

    /**
     * Sauvegarde les données
     */
    public void dataSave() {
        saver.saveData(currentLog.getPlayers());
    }

    /**
     * Start la boucle.
     */
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
        scoreChecker.scoreCourantProperty().addListener((ChangeListener<? super Number>)(observable, oldValue, newValue) ->{
            if (newValue.intValue() > oldValue.intValue()) {
                soundPlayer.setFilePath("rsrc/sound/resources_sounds_score.wav");
                soundPlayer.play();    
            }
        });
        animationBird.propertyStop().addListener((ChangeListener<? super Boolean>)(observable, oldValue, newValue) -> {
            if(newValue){
                setGameOver(newValue);
                soundPlayer.setFilePath("rsrc/sound/resources_sounds_hit.wav");
                soundPlayer.play();
            }
        });
    }

    /**
     * Stop la boucle.
     */
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

    /**
     * Restart la partie.
     */
    public void restartGame() {
        createWorld();
    }

    /**
     * Anime le joueur si appui sur la touche
     *
     * @param keyCode le code de la touche
     */
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
