package model.game.collider;

import model.Position;
import model.game.World;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Collider {

    private World world;

    private DoubleProperty width = new SimpleDoubleProperty();
        public double getWidht() { return width.get();}
        public void setWidth(double width) { this.width.set(width);}
        public DoubleProperty widthProperty() { return width; }

    private DoubleProperty height = new SimpleDoubleProperty();
        public double getHeight() { return height.get();}
        public void setHeight(double width) { this.height.set(width);}
        public DoubleProperty heightProperty() { return height; }

    public abstract boolean canMove(Position pos);

    public abstract boolean checkPos(Position pos);

    public abstract boolean checkCollision(Position pos);

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
