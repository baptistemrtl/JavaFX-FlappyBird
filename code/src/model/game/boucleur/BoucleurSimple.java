package model.game.boucleur;

public class BoucleurSimple extends Boucleur{

    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(getTimer());
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }
}
