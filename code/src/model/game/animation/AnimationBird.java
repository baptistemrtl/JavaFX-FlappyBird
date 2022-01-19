package model.game.animation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
import model.game.collider.Collider;
import model.game.displacer.BirdDisplacer;

/**
 * La classe AnimationBird permet de gérer l'animation du Bird.
 */
public class AnimationBird extends Animation implements InvalidationListener {

    private double yToReach;
    private double currentY;

    private Thread threadFly;
    private Thread threadDrop;

    private final Boucleur dropBoucleur;

    private Boolean isDropping;
    private final BooleanProperty stopProperty = new SimpleBooleanProperty();

    /**
     * Le BooleanProperty de stopProperty.
     *
     * @return Le BooleanProperty de stopProperty
     */
    public BooleanProperty propertyStop(){ return stopProperty; }

    /**
     * Get stop
     *
     * @return stop
     */
    public Boolean getStop(){ return stopProperty.get(); }

    /**
     * Set stop.
     *
     * @param value la nouvelle valeur de stop
     */
    public void setStop(Boolean value){ stopProperty.set(value);}

    /**
     * Get thread fly thread.
     *
     * @return the thread
     */
    public Thread getThreadFly(){ return threadFly; }

    /**
     * Get thread drop thread.
     *
     * @return the thread
     */
    public Thread getThreadDrop(){ return threadDrop; }

    /**
     * Redéfinition du constructeur
     *
     * @param displacer le déplaceur de l'oiseau
     * @param coll      le colisionneur de l'oiseau
     * @param boucleur  le boucleur de l'oiseau
     * @param drop      Deuxième boucleur pour gérer la descente de l'oiseau
     */
    public AnimationBird(BirdDisplacer displacer, Collider coll, BoucleurBird boucleur, BoucleurDrop drop) {
        super(displacer, coll, boucleur);
        this.dropBoucleur = drop;
        dropBoucleur.addListener(this);
        this.boucleur.addListener(this);
    }

    /**
     * Méthode pour mettre en place une "animation" de saut de l'oiseau
     */
    @Override
    public void animate() {
        //Arrêt du thread de drop étant donné qu'il est lancé dès le lancement du jeu
        dropBoucleur.setRunning(false);
        isDropping = false;
        threadDrop.interrupt();

        currentY = collider.getWorld().getCurrentBird().getPos().getY();
        yToReach = currentY - 100.0; // On veut que l'oiseau se déplace d'une hauteur de 100
        displacer.setEnableMove(true); //On autorise le déplaceur à faire bouger l'oiseau

        //Lancement du boucleur de saut
        boucleur.setRunning(true);
        threadFly = new Thread(this.boucleur);
        threadFly.start();
    }

    /**
     * Méthode appelée dès le lancement d'une partie
     * qui va directement faire tomber l'oiseau
     */
    public void initalizeAnimation(){
        setStop(false);
        currentY = collider.getWorld().getCurrentBird().getPos().getY();
        displacer.setEnableMove(true); //Autorisation du déplaceur à faire bouger l'oiseau

        //Lancement du boucleur de chute
        dropBoucleur.setRunning(true);
        isDropping = true;
        threadDrop = new Thread(dropBoucleur);
        threadDrop.start();
    }

    /**
     * Méthode appelée à chaque signal reçu par le boucleur
     * qui va gérer soit l'animation la chute, soit le saut en fonction du
     * booleen isDropping
     *
     * @param observable l'élément observable (ignoré)
     */
    @Override
    public void invalidated(Observable observable) {
        if (isDropping == null){
            isDropping = true; //On ré-établi que de base, l'oiseau chute
        }
       if (!isDropping) {
           if (currentY <= yToReach) {
               if (!displacer.move(collider.getWorld().getCurrentBird(),0.0)){
                   stopAll(); //si l'oiseau a atteint le plus haut point de son vol mais qu'il heure un obstacle, on stoppe tout
               } else {
                   stopAnimation(); //sinon on relance l'animation de drop
               }
           } else if (!displacer.move(collider.getWorld().getCurrentBird(),-10.0)) {
                   stopAll(); //si l'oiseau est en saut, mais qu'il heure un obstacle, on stoppe tout
           } else {
               currentY = currentY - 10; //l'oiseau se déplace de -10 y à chaque signal
           }
       } else {
           if (currentY >= 700) {
               stopAll(); //si l'oiseau n'est plus sur l'écran
           } else if (displacer.move(collider.getWorld().getCurrentBird(),+ 2.8)) {
                   currentY = currentY + 2.8; //En chute, l'oiseau tombe de 2.8 à chasue signal
           } else {
                   stopAll(); //S'il a heurté un obstacle pendant sa chute, on stop
           }
       }
    }

    /**
     * Méthode qui à la fin d'un saut, rétabli le mouvement de l'oiseau en chute
     */
    @Override
    public void stopAnimation() {
        //Arrêt du de vol
        boucleur.setRunning(false);
        threadFly.interrupt();

        //Lancement de la chute
        isDropping = true;
        dropBoucleur.setRunning(true);
        threadDrop = new Thread(dropBoucleur);
        threadDrop.start();
    }

    /**
     * Méthode qui va indiquer que la partie est finie
     */
    public void stopAll() {
        //Interruption des deux threads
        if (threadFly != null) {
            threadFly.interrupt();
        }
        if (threadDrop != null) {
            threadDrop.interrupt();
        }
        boucleur.setRunning(false);
        dropBoucleur.setRunning(false);

        //Indique la fin de partie au Manager
        setStop(true);
    }
}
