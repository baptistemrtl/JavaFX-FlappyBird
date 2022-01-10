package model.game;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import model.Position;
import model.game.element.Bird;
import model.game.element.Element;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.game.element.Obstacle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    private final ObservableMap<Position, Element> elements = FXCollections.observableHashMap();
    private final ObservableList<Element> list; // check si possible avec map

    public World(){
        list = FXCollections.observableArrayList(eventShowable ->
                new Observable[]{eventShowable.getPos().xProperty(),eventShowable.getPos().yProperty() });
    }

    public IntegerProperty timer = new SimpleIntegerProperty();
        public IntegerProperty timerProperty() { return timer; }
        public int getTimer() { return timer.get(); }
        public void setTimer(int timer) { this.timer.set(timer); }


    public void addElement(Element element) {
            elements.put(element.getPos(), element);
    }
    public void addListElement(List<Element> elementList) {
            for(Element e : elementList) {
                addElement(e);
            }
    }
    public void delElement(Element element) {
            elements.remove(element.getPos());
    }

    public ObservableMap<Position,Element> getValues() {
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

    public ObservableList<Element> getValuesList() {
        for(Map.Entry<Position, Element> entry : elements.entrySet()) {
            if (entry.getValue() instanceof Obstacle) {
                list.add(entry.getValue());
            }
        }
        return FXCollections.unmodifiableObservableList(list);
    }

    private Map<Position, Obstacle> getAllObstacles(){
        Map<Position, Obstacle> obstacles = new HashMap<>();
        for(Map.Entry<Position, Element> entry : elements.entrySet()) {
            if (entry.getValue() instanceof Obstacle) {
                obstacles.put(entry.getKey(), (Obstacle) entry.getValue());
            }
        }

        return obstacles;
    }

    public Obstacle getLastObstacle() {
        Map<Position, Obstacle> obstacles = getAllObstacles();
        Position pos = obstacles.keySet().stream().findFirst().get();
        Obstacle obs = null;

        for(Map.Entry<Position, Obstacle> entry : obstacles.entrySet()) {
            if (entry.getKey().getX() >= pos.getX()) { // unique ou nom ?
                obs = entry.getValue();
                pos = entry.getKey();
            }
        }

        return obs;
    }

    public int getNumberOfObstacle(){
        int i = 0;
        for(Element obstacle : elements.values()) {
            if (obstacle instanceof Obstacle) {
                ++i;
            }
        }

        return i;
    }

    public Obstacle getFirstDownPipe() {
        Obstacle obs = getLastObstacle();
        for(Map.Entry<Position, Obstacle> entry : getAllObstacles().entrySet()) {
            if (entry.getKey().getY() == 0) {
                if (entry.getKey().getX() <= obs.getPos().getX()) { // unique ou nom ?
                    obs = entry.getValue();
                }
            }
        }

        return obs;
    }

    public Obstacle getFirstUpPipe() {
        Obstacle obs = getFirstDownPipe();
        for(Map.Entry<Position, Obstacle> entry : getAllObstacles().entrySet()) {
            if (entry.getKey().getX() == obs.getPos().getX()) {
                return entry.getValue();
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
