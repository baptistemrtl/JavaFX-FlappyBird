package model.game.manager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.game.World.World;

public class ScoreChecker implements Runnable{

    public IntegerProperty scoreCourant = new SimpleIntegerProperty();
        public int getScoreCourant(){ return scoreCourant.get();}
        public void setScoreCourant(int value){ scoreCourant.set(value);}
        public IntegerProperty scoreCourantProperty() { return scoreCourant; }

    private Boolean running;
    private World world;

    public ScoreChecker(World world){
        this.world = world;
    }

    @Override
    public void run() {
        while(isRunning()) {
            try {
                Thread.sleep(400);
                checkScore();
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

    public void checkScore(){
        setScoreCourant(world.getNumberOfObstaclePassed());
    }

    public Boolean isRunning(){
        return running;
    }

    public void setRunning(Boolean value){
        running = value;
    }

}
