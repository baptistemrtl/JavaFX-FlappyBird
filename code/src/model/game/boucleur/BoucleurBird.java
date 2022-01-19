package model.game.boucleur;

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
