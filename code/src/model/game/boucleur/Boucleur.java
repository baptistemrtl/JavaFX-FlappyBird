package model.game.boucleur;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui va nous permettre d'avoir des boucles de jeu sur les déplacements d'objets
 */
public abstract class Boucleur implements Runnable, Observable {
    protected final List<InvalidationListener> listeners = new ArrayList<>();
    private boolean running = false;

    //Temps d'intervalle entre chasue envoie de signal
    private int timer;
    public int getTimer() {
        return timer;
    }
    public void setTimer(int time) {
        timer = time;
    }

    //Définition et récupération de l'état du boucleur
    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean isRunning() {
        return running;
    }

    /**
     * Ajout d'un écouteur à qui envoyer un signal
     * @param listener
     */
    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    /**
     * Suppression d'un écouteur
     * @param listener
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    /***
     * Méthode qui envoie le signal à l'écouteur des classes filles
     */
    public void beep(){
        listeners.forEach(o-> Platform.runLater(()-> o.invalidated(this)));
    };

}
