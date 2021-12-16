package model.game;

import com.sun.source.tree.Tree;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.Position;
import model.game.element.Element;

import java.util.TreeMap;

public class World {
    //private final TreeMap<Position, Element> elements = new TreeMap<>();
    private final ObservableMap<Position, Element> elements = FXCollections.observableHashMap();
    public IntegerProperty timer = new SimpleIntegerProperty();
        public IntegerProperty timerProperty() {
            return timer;
        }
        public int getTimer() { return timer.get(); }
        public void setTimer(int timer) {this.timer.set(timer); }


    /*public void addElement(Element element) {
        elements.put(element.getPos(), element);
    }*/

    public void addElement(Element element){
        elements.put(element.getPos(), element);
    }
    public void delElement(Element element){
        elements.remove(element.getPos());
    }

    /*public Element asElement(Position pos) {
        return elements.get(pos);
    }*/

    public ObservableMap<Position, Element> getElements(){
        return FXCollections.unmodifiableObservableMap(elements);
    }

    public boolean decrementationTimer(){
        if(getTimer() == 0){
            return false;
        }
        setTimer(getTimer()-1);
        return true;
    }

}
