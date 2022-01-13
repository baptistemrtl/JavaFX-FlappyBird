package model.game.animation;

import model.game.boucleur.BoucleurBird;

public class AnimationBird extends Animation {

    //Position : ax^2 + bx + c
    //vitesse : ax+b
    //acceleration : a

    //vitesse : distance * temps <=> temps = vitesse/distance (on aurait juste à ajouter un private double velocity)
    //sachant que le temps va rester le même mais que a chaque beep la distance va diminuer (currentBirdY - yToReach)

    @Override
    public void animate() {
        //new Thread(new BoucleurBird()).start();
    }
}
