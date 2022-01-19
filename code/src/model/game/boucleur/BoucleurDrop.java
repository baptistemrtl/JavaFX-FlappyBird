package model.game.boucleur;

import javafx.application.Platform;

/**
 * Boucleur sur la chute d'un oiseau
 */
public class BoucleurDrop extends Boucleur{

    /**
     * Méthode qui envoie un signal à l'écouteur toutes les 10 millisecondes
     */
    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

}
