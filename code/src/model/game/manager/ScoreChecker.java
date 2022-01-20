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

    /**
     * Get le scoreCourant
     *
     * @return le scoreCourant
     */
    public int getScoreCourant(){ return scoreCourant.get();}

    /**
     * Set le scoreCourant
     *
     * @param value la nouvelle valeur
     */
    public void setScoreCourant(int value){ scoreCourant.set(value);}

    /**
     * L'IntegerProperty du scoreCourant
     *
     * @return L'IntegerProperty
     */
    public IntegerProperty scoreCourantProperty() { return scoreCourant; }

    private Boolean running;
    private final World world;

    /**
     * Constructeur de ScoreChecker
     *
     * @param world le monde
     */
    public ScoreChecker(World world){
        this.world = world;
    }

    /**
     * Getter de running
     *
     * @return la boolean running
     */
    public Boolean isRunning() {
        return running;
    }

    /**
     * Set running
     *
     * @param value la nouvelle valeur
     */
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
