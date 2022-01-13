package model.game.boucleur;

import javafx.application.Platform;

public class BoucleurDrop extends Boucleur{

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

    @Override
    public void beep(){
        //listeners.forEach(o-> Platform.runLater(()-> o.invalidated(this)));
        listeners.forEach(o-> o.invalidated(this));
    }
}
