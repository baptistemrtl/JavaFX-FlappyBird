package model.game.collider;

import model.game.element.Position;
import model.game.World.World;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Collider {

    private World world;

    public abstract boolean canMove(Position pos);

    protected abstract boolean checkPos(Position pos);

    protected abstract boolean checkCollision(Position pos); //test

    public Collider(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
