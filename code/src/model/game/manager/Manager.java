package model.game.manager;

import Persistance.LoaderBinaire;
import Persistance.SaverBinaire;
import javafx.beans.property.*;
import javafx.util.StringConverter;
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

import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

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
    private final Collider collider;
    private final Boucleur boucleur = new BoucleurObstacle();
    private final Boucleur birdBoucleur = new BoucleurBird();
    private Log currentLog;
    private final BirdDisplacer birdDeplaceur ;
    private final  Displacer obstacleDisplacer;
    private Animation animationObs;
    private AnimationBird animationBird;
    private ScoreChecker scoreChecker;

    private LoaderBinaire loader;
    private SaverBinaire saver;

    private int compteurBoucl = 0;
    private int compteurCrea = 0;

    public StringProperty stringScore = new SimpleStringProperty();

   public StringProperty stringScoreProperty(){ return stringScore; }
        /*public void setStringScore(){ stringScore.set(String.valueOf(score.get())); }
        public String getStringScore(){ return String.valueOf(score.get()); }*/


    public Manager() {
        currentWorld = new World();
        collider = new ColliderSimple(currentWorld);
        currentBird = currentWorld.getCurrentBird();
        birdDeplaceur = new BirdDisplacer(collider);
        obstacleDisplacer = new ObstacleDisplacer(collider);
        currentLog = new LogSimple();
        animationObs = new AnimationObstacle((ObstacleDisplacer) obstacleDisplacer,collider,(BoucleurObstacle) boucleur);
        animationBird = new AnimationBird(birdDeplaceur,collider,(BoucleurBird) birdBoucleur,new BoucleurDrop());
    }

    //Initialisation

    public void createWorld(){
        System.out.println("----------");
        collider.getWorld().restartWorld();
        currentBird = collider.getWorld().getCurrentBird();
        animationObs.setCollider(collider);
        animationBird.setCollider(collider);
        scoreChecker = new ScoreChecker(collider.getWorld());
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
        return collider.getWorld().getCurrentBird();
    }


    public World getCurrentWorld() {
        return collider.getWorld();
    }

    public List<Obstacle> getAllObstacles() {
        List<Obstacle> list = new ArrayList<>();
        ObservableList<Element> elements = collider.getWorld().getElements();

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
    public void startBoucle() { // = startGame
        setGameOver(false);
        animationObs.animate();
        animationBird.initalizeAnimation();
        scoreChecker.setRunning(true);
        scoreChecker.setScoreCourant(0);
        stringScore.bindBidirectional(scoreChecker.scoreCourantProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return " " + object.toString();
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });
        new Thread(scoreChecker).start();
    }

    public void stopBoucle() {
        animationBird.stopAll();
        scoreChecker.setRunning(false);
        animationObs.stopAnimation();
    }

    public void restartGame(){
        stopBoucle();
        createWorld();
        animationObs = new AnimationObstacle((ObstacleDisplacer) obstacleDisplacer,collider,(BoucleurObstacle) boucleur);
        animationBird = new AnimationBird(birdDeplaceur,collider,(BoucleurBird) birdBoucleur,new BoucleurDrop());
        animationObs.setCollider(collider);
        animationBird.setCollider(collider);
        startBoucle();
        setGameOver(false);
    }


    public void keyMove(KeyCode keyCode) {
        if (isGameOver()) {
            return;
        }

        if (keyCode == KeyCode.SPACE) {
            animationBird.animate();
            if (animationBird.getThreadFly().isInterrupted() && animationBird.getThreadDrop().isInterrupted()) {
                gameOver.set(true);
            }
        }
    }
}
