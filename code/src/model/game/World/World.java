package model.game.World;

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

import java.util.*;

public class World {

    private ObservableList<Element> elements;

    public World(){
        elements = FXCollections.observableArrayList(eventShowable ->
                new Observable[]{eventShowable.getPos().xProperty(),eventShowable.getPos().yProperty() });
    }



    public void addElement(Element element) {
            /*if (elements.size() == 0){
                elements.add(element);
            }
            int index = 0;
            for (Element value : elements){
                if (element.getPos().getX() <= value.getPos().getX()){
                    elements.add(index,element);
                }
                ++index;

            }*/

        elements.add(element);
    }

    public void addListElement(List<Element> elementList) {
        System.out.println("addList");
            for(Element e : elementList) {
                addElement(e);
            }
    }

    public void delElement(Element element) {
        System.out.println("del");
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
            return FXCollections.observableList(elements);
    }

    public Element getLastElementX(){
        int maxX = 0;
        int index = 0;
        if (elements.isEmpty()){
            return null;
        }
        for (Element elm : elements){
            if (elm.getPos().getX() > maxX){
                maxX = (int) elm.getPos().getY();
                ++index;
            }
        }
        return elements.get(index-1);

    }

    private int getIndexOfObstacles(Obstacle obs, LinkedList<Obstacle> obstacles){
            int index = 0;
            for (Obstacle value : obstacles){
                    if (obs.getPos().getX() > value.getPos().getX()){
                        ++index;
                    }
                    else
                        return index;
            }
            return index;
    }

    private void sortList(List<Element> elements){
        elements.sort(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if (o1.getPos().getX() < o2.getPos().getX()){
                    return -1;
                }
                if (o1.getPos().getX() > o2.getPos().getX()){
                    return 1;
                }
                return 0;
            }
        });
    }


}
