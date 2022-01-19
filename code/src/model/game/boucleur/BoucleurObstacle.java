package model.game.boucleur;

import javafx.application.Platform;

/**
 * Boucleur sur le déplacement des obstacles
 */
public class BoucleurObstacle extends Boucleur{

    /**
     * Méthode qui envoie un signal à l'écouteur toutes les 45 millisecondes
     * au début et va diminuer le temps entre chaque signal petit à petit
     */
    @Override
    public void run() {
        double speed = 0;
        while(isRunning()) {
            beep();
            try {
                Thread.sleep((long)(45 - speed));
            }
            catch (InterruptedException e){
                setRunning(false);
            }

            if(speed < 35) {
                speed += 0.015;
            }
        }
    }

}
