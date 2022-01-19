package model.game.collider;

import model.game.element.Position;
import model.game.World.World;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe qui va gérer les collisions entre l'oiseau et les obstacles
 */
public abstract class Collider {

    //Monde actuel
    private World world;

    public Collider(World world) {
        this.world = world;
    }

    //Méthodes qui vont permettre d'informer la possibilité ou non de déplacement d'un élément
    public abstract boolean canMove(Position pos);
    protected abstract boolean checkPos(Position pos);
    protected abstract boolean checkCollision(Position pos); //test


    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
}
