package model.game.boucleur;

public class BoucleurSimple extends Boucleur{
    @Override
    public void run() {
        while(isRunning()){
            beep();
            try{
                Thread.sleep(50); //a définir j'ai mis 50 comme ça
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }
}
