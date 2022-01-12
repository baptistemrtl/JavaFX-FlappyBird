package model.game.boucleur;

public class BoucleurBird extends Boucleur{

    private double yToReach;
    private double time;




    @Override
    public void beep() {
        //deplacement de l'oiseau de 1
    }

    @Override
    public void run() {
        //on set false quand l'oiseau a atteint son point Y qu'il devait atteindre
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
}
