package model.game.boucleur;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Boucleur implements Runnable, Observable {
    private List<InvalidationListener> listeners = new ArrayList<>();
    private boolean running = false;

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isRunning(){
        return running;
    }

    @Override
    public void addListener(InvalidationListener listener){
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener){
        listeners.remove(listener);
    }

    public void beep(){
        listeners.forEach(o -> Platform.runLater(()-> o.invalidated(this)));
    }
}