package model.game;

import javafx.collections.ObservableList;
import model.Position;
import model.game.element.Bird;
import model.game.element.Element;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.game.element.Obstacle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class World {

    private final ObservableMap<Position, Element> elements = FXCollections.observableHashMap();

    public IntegerProperty timer = new SimpleIntegerProperty();
        public IntegerProperty timerProperty() { return timer; }
        public int getTimer() { return timer.get(); }
        public void setTimer(int timer) { this.timer.set(timer); }


    public void addElement(Element element) {
            elements.put(element.getPos(), element);
    }
    public void addListElement(List<Element> elementList){
            for(Element e : elementList){
                addElement(e);
            }
    }
    public void delElement(Element element) {
            elements.remove(element.getPos());
    }

    public ObservableMap<Position,Element> getValues(){
        return FXCollections.unmodifiableObservableMap(elements);
    }

    public Bird getCurrentBird() {
            for(Map.Entry<Position, Element> entry : elements.entrySet()) {
                if (entry.getValue() instanceof Bird) {
                    return (Bird) entry.getValue();
                }
            }
            return null;
    }

    public void replaceCurrentBird(Bird bird) {
            Position cbPos = getCurrentBird().getPos();
            elements.replace(cbPos,bird);
    }

    public ObservableMap<Position, Element> getElements() {
            return FXCollections.unmodifiableObservableMap(elements);
    }

    private List getAllObstacles(){
        List<Obstacle> obstacles = new ArrayList();
        for(Map.Entry<Position, Element> entry : elements.entrySet()) {
            if (entry.getValue() instanceof Obstacle) {
                obstacles.add((Obstacle) entry.getValue());
            }
        }
        return obstacles;
    }

    public Obstacle getLastObstacle(){
        List list = getAllObstacles();
        Iterator<Obstacle> iterator = list.iterator();
        Obstacle last = iterator.next();
        while (iterator.hasNext()){
            last = iterator.next();
        }
        return last;
    }

    public int getNumberOfObstacle(){
        int i = 0;
        for(Map.Entry<Position, Element> entry : elements.entrySet()) {
            if (entry.getValue() instanceof Obstacle) {
                ++i;
            }
        }
        return i;
    }

    public Obstacle getFirstDownPipe(){
        Obstacle obs = getLastObstacle();
        for(Map.Entry<Position, Element> entry : elements.entrySet()) {
            if (entry.getValue() instanceof Obstacle) {
                if (entry.getValue().getPos().getY() == 0){
                    if (entry.getValue().getPos().getX() <= obs.getPos().getX()){
                        obs = (Obstacle) entry.getValue();
                    }
                }
            }
        }
        return obs;
    }

    public Obstacle getFirstUpPipe(){
        Obstacle obs = getFirstDownPipe();
        for(Map.Entry<Position, Element> entry : elements.entrySet()){
            if (entry.getValue().getPos().getX() == obs.getPos().getX()){
                return (Obstacle) entry.getValue();
            }
        }
        return null;
    }

    // Pour l'instant inutile donc surement à suppp mais a voir
    public boolean decrementationTimer() {
        if(getTimer() == 0) {
            return false;
        }

        setTimer(getTimer()-1);
        return true;
    }
}
