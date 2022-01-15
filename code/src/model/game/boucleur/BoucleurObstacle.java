package model.game.boucleur;

import javafx.application.Platform;

public class BoucleurObstacle extends Boucleur{

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
                speed += 0.01;
            }
        }
    }

}
