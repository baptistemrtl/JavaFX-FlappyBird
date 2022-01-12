package model.game.boucleur;

import javafx.application.Platform;

public class BoucleurObstacle extends Boucleur{

    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

    @Override
    public void beep() {
        listeners.forEach(o-> Platform.runLater(()-> o.invalidated(this)));
    }
}
