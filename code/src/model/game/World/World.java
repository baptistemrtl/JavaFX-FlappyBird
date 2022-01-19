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

/**
 * Classe qui représente notre monde,
 * et qui contient donc une liste d'Element
 */
public class World {

    /**
     * Liste d'Element
     */
    private final ObservableList<Element> elements;
    private final Creator creator = new CreatorRandom();

    /**
     * Gets elements.
     *
     * @return the elements
     */
    public ObservableList<Element> getElements() {
        return elements;
    }

    /**
     * Constructeur qui défini la liste d'Element comme une FXCollections.observableArrayList
     * pour ajouter un listener dessus
     */
    public World() {
        elements = FXCollections.observableArrayList(creator.createWorld());
    }

    /**
     * Méthode qui reset le monde en supprimant tous les elements déjà présent et en en ajoutant des nouveaux
     * On ne redéfinit pas elements de la manière suivante : FXCollections.observableArrayList(creator.createWorld());
     * car cela créérait des problèmes au niveau du listener
     */
    public void restartWorld() {
        while(!elements.isEmpty()){
            elements.remove(0);
        }
        elements.addAll(creator.createWorld());

    }

    /**
     * Ajout d'un couple d'obstacle à la liste d'éléments du monde
     */
    public void addObstacles() {
        creator.createObstacle(elements);
    }

    /**
     * Ajout d'un seul élément à la liste d'Elément
     *
     * @param element the element
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Récupération de l'oiseau instancié dans la liste d'Element
     *
     * @return current bird
     */
    public Bird getCurrentBird() {
        for(Element element : elements) {
            if (element instanceof Bird) {
                return (Bird) element;
            }
        }

        return null;
    }

    /**
     * Récupération du nombre d'obstacle passé par l'oiseau
     *
     * @return implicitement, le score actuel
     */
    public int getNumberOfObstaclePassed() {
        int value = 0;
        Bird cb = getCurrentBird();
        for (Element elem : elements) {
            if (elem instanceof Obstacle) {
                if (cb.getPos().getX() >= (elem.getPos().getX())) {
                    ++value;
                }
            }
        }
        return value/2; // On divise par deux, pour que la valeur de retour représente le score
    }
}
