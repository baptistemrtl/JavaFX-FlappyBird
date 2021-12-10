package model.game;

import com.sun.source.tree.Tree;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Position;
import model.game.element.Element;

import java.util.TreeMap;

public class World {
    //private final TreeMap<Position, Element> elements = new TreeMap<>();
    private ObservableList<Element> elements = FXCollections.observableArrayList();
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
        elements.add(element);
    }
    public void delElement(Element element){
        elements.remove(element);
    }

    /*public Element asElement(Position pos) {
        return elements.get(pos);
    }*/

    public ObservableList<Element> getElements(){
        return FXCollections.unmodifiableObservableList(elements);
    }

    public boolean decrementationTimer(){
        if(getTimer() == 0){
            return false;
        }
        setTimer(getTimer()-1);
        return true;
    }

}
