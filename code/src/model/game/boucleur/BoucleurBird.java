package model.game.boucleur;

import javafx.application.Platform;
import model.game.animation.AnimationBird;

import java.util.function.Consumer;

public class BoucleurBird extends Boucleur{

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
