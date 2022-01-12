package model.game.creator;


import model.game.element.Element;

import java.util.List;

public abstract class Creator {

    public abstract List<Element> createWorld();

    public abstract void createObstacle(List<Element> elements);
}
