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

    private Boucleur dropBoucleur;

    private Boolean isDropping;

    public AnimationBird(BirdDisplacer displacer, Collider coll, BoucleurBird boucleur, BoucleurDrop drop) {
        super(displacer, coll, boucleur);
        this.dropBoucleur = drop;
        dropBoucleur.addListener(this);
        this.boucleur.addListener(this);
        threadFly = new Thread(boucleur);
        threadDrop = new Thread(dropBoucleur);
    }



    //Position : ax^2 + bx + c
    //vitesse : ax+b
    //acceleration : a

    //vitesse : distance * temps <=> temps = vitesse/distance (on aurait juste à ajouter un private double velocity)
    //sachant que le temps va rester le même mais que a chaque beep la distance va diminuer (currentBirdY - yToReach)

    @Override
    public void animate() {
        System.out.println("animate");
        dropBoucleur.setRunning(false);
        threadDrop.interrupt();
        currentY = collider.getWorld().getCurrentBird().getPos().getY();
        System.out.println(currentY);
        yToReach = currentY - 50.0;
        System.out.println(yToReach);
        displacer.setEnableMove(true);
        boucleur.setRunning(true);
        isDropping = false;
        threadFly.start();
        System.out.println("FLY");
    }

    @Override
    public void invalidated(Observable observable) {
       if (!isDropping){
           if (currentY == yToReach){
               if (!displacer.move(collider.getWorld().getCurrentBird(),0.2)){
                   stopAll();
               }
               else{
                   stopAnimation();
               }
           }
           else {
               if (!displacer.move(collider.getWorld().getCurrentBird(),-1.0)){
                   System.out.println("2");
                   stopAll();
               }
               else
                   currentY = currentY -1;
                   //currentY = collider.getWorld().getCurrentBird().getPos().getY();
           }
       }
       else
       {
           System.out.println(collider.getWorld().getCurrentBird().getPos().getY());
           if (currentY == yToReach){
               stopAll();
           }
           else{
               if (displacer.move(collider.getWorld().getCurrentBird(),1.5)){
                   //currentY = collider.getWorld().getCurrentBird().getPos().getY();
                   currentY += 1.5;
               }
               else{
                   stopAll();
               }
           }
       }
    }

    public void stopAnimation(){
        System.out.println("stop anim");
        yToReach = 700;
        boucleur.setRunning(false);
        threadFly.interrupt();
        isDropping = true;
        dropBoucleur.setRunning(true);
        threadDrop.start();
    }

    public void stopAll(){
        System.out.println("stopall");
        threadFly.interrupt();
        threadDrop.interrupt();
        boucleur.setRunning(false);
        dropBoucleur.setRunning(false);
        System.out.println(collider.getWorld().getCurrentBird().getPos().getY());
    }
}
