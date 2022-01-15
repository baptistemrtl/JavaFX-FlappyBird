package model.game.animation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import launcher.Launch;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.displacer.Displacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.element.Element;
import model.game.element.Obstacle;

public class AnimationObstacle extends Animation implements InvalidationListener {

    private Thread moveThread;
    private int compteurBoucle = 0;
    private int moduloBoucle;

    public AnimationObstacle(ObstacleDisplacer displacer, Collider coll, BoucleurObstacle boucleur) {
        super(displacer, coll, boucleur);
        this.boucleur.addListener(this);
        moveThread = new Thread(boucleur);
    }

    @Override
    public void animate() {
        moduloBoucle = 60;
        boucleur.setRunning(true);
        moveThread.start();
    }

    @Override
    public void invalidated(Observable observable) {
        for (Element element : collider.getWorld().getElements()) {
            if (element instanceof Obstacle) {
                if (!displacer.move(element,5.0)) {
                    stopAnimation();
                }
            }
        }
        if (compteurBoucle%moduloBoucle == 0){
            collider.getWorld().addObstacles();
        }
        if (compteurBoucle%1600 == 0){
            moduloBoucle = moduloBoucle - 2;
        }
        ++compteurBoucle;
    }

    @Override
    public void stopAnimation(){
        boucleur.setRunning(false);
        moveThread.interrupt();
    }
}
