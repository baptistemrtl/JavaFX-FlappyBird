package model.game.boucleur;

import javafx.application.Platform;

public class BoucleurObstacle extends Boucleur{

    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(45);
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

}
