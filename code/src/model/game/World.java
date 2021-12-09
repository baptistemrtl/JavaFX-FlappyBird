package model.game;

import com.sun.source.tree.Tree;
import model.Position;
import model.game.element.Element;

import java.util.TreeMap;

public class World {
    private final TreeMap<Position, Element> elements = new TreeMap<>();

    public void addElement(Element element) {
        elements.put(element.getPos(), element);
    }

    public Element asElement(Position pos) {
        return elements.get(pos);
    }
}
