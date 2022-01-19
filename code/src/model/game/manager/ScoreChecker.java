package model.game.manager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.game.World.World;

/**
 * Classe qui va mettre à jour le scoreCourant
 */
public class ScoreChecker implements Runnable {

    /**
     * Propriété de scoreCourant pour le binding
     */
    public IntegerProperty scoreCourant = new SimpleIntegerProperty();
        public int getScoreCourant(){ return scoreCourant.get();}
        public void setScoreCourant(int value){ scoreCourant.set(value);}
        public IntegerProperty scoreCourantProperty() { return scoreCourant; }

    private Boolean running;
    private World world;

    public ScoreChecker(World world){
        this.world = world;
    }

    public Boolean isRunning() {
        return running;
    }

    public void setRunning(Boolean value){
        running = value;
    }

    /**
     * Méthode qui va appeler la méthode de mise à jour du score toutes les 500ms
     */
    @Override
    public void run() {
        while(isRunning()) {
            try {
                Thread.sleep(500);
                checkScore();
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

    /**
     * Mise à jour du scoreCourant
     */
    public void checkScore() {
        setScoreCourant(world.getNumberOfObstaclePassed());
    }

}
