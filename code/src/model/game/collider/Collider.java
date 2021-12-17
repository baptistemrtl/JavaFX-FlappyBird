package model.game.collider;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import jdk.jshell.spi.ExecutionControl;
import model.Position;
import model.game.World;

public abstract class Collider {

    private World world;

    private DoubleProperty width = new SimpleDoubleProperty();
        public double getWidht(){ return width.get();}
        public void setWidth(double width){ this.width.set(width);}
        public DoubleProperty widthProperty(){ return width; }

    private DoubleProperty height = new SimpleDoubleProperty();
        public double getHeight(){ return height.get();}
        public void setHeight(double width){ this.height.set(width);}
        public DoubleProperty heightProperty(){ return height; }

    public abstract boolean canMove(Position pos);

    public abstract boolean canMoveBird(Position pos);

    public abstract void isPresent(Position pos);

    public Collider(World world){
        this.world = world;
    }

}
