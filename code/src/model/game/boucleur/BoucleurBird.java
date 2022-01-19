package model.game.boucleur;

import javafx.application.Platform;
import model.game.animation.AnimationBird;

import java.util.function.Consumer;

/**
 * Boucleur sur l'envol d'un oiseau
 */
public class BoucleurBird extends Boucleur{

    /**
     * Méthode qui envoie un signal à l'écouteur toutes les 20 millisecondes
     */
    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

}
