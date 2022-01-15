package model.game.animation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import launcher.Launch;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
import model.game.collider.Collider;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;

public class AnimationBird extends Animation implements InvalidationListener {

    private double yToReach;
    private double currentY;

    private Thread threadFly;
    private Thread threadDrop;

    private final Boucleur dropBoucleur;

    private Boolean isDropping;

    public AnimationBird(BirdDisplacer displacer, Collider coll, BoucleurBird boucleur, BoucleurDrop drop) {
        super(displacer, coll, boucleur);
        this.dropBoucleur = drop;
        dropBoucleur.addListener(this);
        this.boucleur.addListener(this);
    }

    public Thread getThreadFly(){ return threadFly; }
    public Thread getThreadDrop(){ return threadDrop; }


    //Position : ax^2 + bx + c
    //vitesse : ax+b
    //acceleration : a

    //vitesse : distance * temps <=> temps = vitesse/distance (on aurait juste à ajouter un private double velocity)
    //sachant que le temps va rester le même mais que a chaque beep la distance va diminuer (currentBirdY - yToReach)

    @Override
    public void animate() {
        dropBoucleur.setRunning(false);
        threadDrop.interrupt();
        currentY = collider.getWorld().getCurrentBird().getPos().getY();

        yToReach = currentY - 100.0;
        displacer.setEnableMove(true);
        boucleur.setRunning(true);
        isDropping = false;
        threadFly = new Thread(this.boucleur);
        threadFly.start();
    }

    public void initalizeAnimation(){
        currentY = collider.getWorld().getCurrentBird().getPos().getY();
        displacer.setEnableMove(true);
        dropBoucleur.setRunning(true);
        isDropping = true;
        threadDrop = new Thread(dropBoucleur);
        threadDrop.start();
    }

    @Override
    public void invalidated(Observable observable) {
       if (!isDropping) {
           if (currentY <= yToReach) {
               if (!displacer.move(collider.getWorld().getCurrentBird(),0.0)){
                   stopAll();
               } else {
                   stopAnimation();
               }
           } else if (!displacer.move(collider.getWorld().getCurrentBird(),-10.0)) {
                   stopAll();
           } else {
               currentY = currentY - 10;
               //currentY = collider.getWorld().getCurrentBird().getPos().getY();
           }
       } else {
           //System.out.println(collider.getWorld().getCurrentBird().getPos().getY());
           if (currentY >= 700) {
               stopAll();
           } else if (displacer.move(collider.getWorld().getCurrentBird(),+ 2.8)) {
                   //currentY = collider.getWorld().getCurrentBird().getPos().getY();
                   currentY = currentY + 2.8;
           } else {
                   stopAll();
           }
       }
    }

    @Override
    public void stopAnimation() {
        boucleur.setRunning(false);
        threadFly.interrupt();
        isDropping = true;
        dropBoucleur.setRunning(true);
        threadDrop = new Thread(dropBoucleur);
        threadDrop.start();
    }

    public void stopAll() {
        if (threadFly != null) {
            threadFly.interrupt();
        }
        if (threadDrop != null) {
            threadDrop.interrupt();
        }

        boucleur.setRunning(false);
        dropBoucleur.setRunning(false);
        System.out.println("STOP");
    }
}
