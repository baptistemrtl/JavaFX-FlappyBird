package model.game.collider;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import jdk.jshell.spi.ExecutionControl;
import model.Position;
import model.game.World;

public class ColliderSimple extends Collider{

    public ColliderSimple(World world){
        super(world);
    }
    @Override
    public boolean canMove(Position pos) {
        return pos.getX()>0 && pos.getX()<=getWidht() && pos.getY()>0 && pos.getY()<=getHeight(); // a compléter  dans l'ensemble c'est  ça  mais faut check le Y
    }

    @Override
    public boolean canMoveBird(Position pos){
        isPresent(pos);
        return canMove(pos);
    }

    @Override
    public void isPresent(Position pos) { //Va parcourir les entités du monde

    }
}
