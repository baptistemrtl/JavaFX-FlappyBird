package model.game.animation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.displacer.ObstacleDisplacer;
import model.game.element.Element;
import model.game.element.Obstacle;

/**
 * La classe AnimationObstacle permet de gérer les animations d'un obstacle.
 */
public class AnimationObstacle extends Animation implements InvalidationListener {

    private Thread moveThread;
    private int compteurBoucle;
    private int moduloBoucle = 60;

    /**
     * Redéfinition du constructeur
     *
     * @param displacer le déplaceur de l'obstacle
     * @param coll      le colisionneur de l'obstacle
     * @param boucleur  le boucleur de l'obstacle
     */
    public AnimationObstacle(ObstacleDisplacer displacer, Collider coll, BoucleurObstacle boucleur) {
        super(displacer, coll, boucleur);
        this.boucleur.addListener(this);
    }

    /**
     * Lancement du déplacement d'obstacles continu
     */
    @Override
    public void animate() {
        compteurBoucle = 1;
        boucleur.setRunning(true);
        moveThread = new Thread(boucleur);
        moveThread.start();
    }

    /**
     * Méthode appelée à chaque signal reçu
     *
     * @param observable l'objet observable (ignoré)
     */
    @Override
    public void invalidated(Observable observable) {
        for (Element element : collider.getWorld().getElements()) {
            if (element instanceof Obstacle) {
                if (!displacer.move(element,5.0)) {
                    stopAnimation(); //Si l'obstacle n'a pas pu se déplacer, on stop l'animation
                }
            }
        }
        //Ajout d'obstacle tous les moduloBoucle signals reçus
        if (compteurBoucle%moduloBoucle == 0) {
            collider.getWorld().addObstacles();
        }
        //Accélération de l'ajout d'obstacle
        if (compteurBoucle%1600 == 0) {
            moduloBoucle = moduloBoucle - 1;
        }

        ++compteurBoucle;
    }

    /**
     * Méthode qui stop thread et boucleur
     */
    @Override
    public void stopAnimation() {
        boucleur.setRunning(false);

        if (moveThread != null) {
            moveThread.interrupt();
        }
    }
}
