package model.game.World;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import launcher.Launch;
import model.game.creator.Creator;
import model.game.creator.CreatorRandom;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.*;

public class World {

    private final ObservableList<Element> elements;

    public World() {
        elements = FXCollections.observableArrayList(creator.createWorld());
        for (Element elem : elements) {
            System.out.println(elem.getImage());
        }
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
        elements.remove(element.getPos());
    }

    public Bird getCurrentBird() {
        for(Element element : elements) {
            if (element instanceof Bird) {
                return (Bird) element;
            }
        }

        return null;
    }

    public ObservableList<Element> getElements() {
        return elements;
    }

    private int getIndexOfObstacles(Obstacle obs, LinkedList<Obstacle> obstacles) {
        int index = 0;
        for (Obstacle value : obstacles) {
                if (obs.getPos().getX() > value.getPos().getX()) {
                    ++index;
                } else {
                    return index;
                }
        }

        return index;
    }

    private void sortList(List<Element> elements) {
        elements.sort(Comparator.comparingDouble(o -> o.getPos().getX()));
    }


}
