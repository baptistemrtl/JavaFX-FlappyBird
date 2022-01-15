package model.game.World;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import model.game.creator.Creator;
import model.game.creator.CreatorRandom;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class World {

    private ObservableList<Element> elements;
    private final Creator creator = new CreatorRandom();

    public World() {
        elements = FXCollections.observableArrayList(creator.createWorld());
    }

    public void restartWorld() {
        elements = FXCollections.observableArrayList(creator.createWorld());
    }

    public void addObstacles() {
        creator.createObstacle(elements);
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void addListElement(List<Element> elementList) {
        for(Element e : elementList) {
            addElement(e);
        }
    }

    public void delElement(Element element) {
        elements.remove(element);
        System.out.println("remove world");
    }

    public Bird getCurrentBird() {
        for(Element element : elements) {
            if (element instanceof Bird) {
                return (Bird) element;
            }
        }

        return null;
    }

    public int getNumberOfObstaclePassed(){
        int value = 0;
        Bird cb = getCurrentBird();
        for (Element elem : elements){
            if (elem instanceof Obstacle){
                if (cb.getPos().getX() >= (elem.getPos().getX()-20)){
                    ++value;
                }
            }
        }
        return value/2;
    }

    public ObservableList<Element> getElements() {
        return elements;
    }


}
