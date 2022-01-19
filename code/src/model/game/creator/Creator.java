package model.game.creator;


import model.game.element.Element;

import java.util.List;

/**
 * Classe qui va gérer la création d'un monde
 */
public abstract class Creator {

    public abstract List<Element> createWorld();

    public abstract void createObstacle(List<Element> elements);
}
