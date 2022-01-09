package model.game.boucleur;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Boucleur implements Runnable, Observable {
    private final List<InvalidationListener> listeners = new ArrayList<>();
    private boolean running = false;
    private int timer = 50;

    public int getTimer() {
        return timer;
    }

    public void setTimer(int time) {
        timer = time;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    public void beep() {
        listeners.forEach(o -> Platform.runLater(()-> o.invalidated(this)));
    }
}
