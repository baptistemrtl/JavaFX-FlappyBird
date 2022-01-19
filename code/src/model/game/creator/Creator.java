package model.game.creator;

import model.game.element.Element;

import java.util.List;

/**
 * Classe qui va gérer la création d'un monde
 */
public abstract class Creator {

    /**
     * Créer des éléments du monde
     *
     * @return la liste des éléments
     */
    public abstract List<Element> createWorld();

    /**
     * Créer les obstacles du monde
     *
     * @param elements la liste des obstacles
     */
    public abstract void createObstacle(List<Element> elements);
}
