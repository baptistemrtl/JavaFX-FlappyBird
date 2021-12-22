package model.game.collider;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableMap;
import jdk.jshell.spi.ExecutionControl;
import model.Position;
import model.game.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.awt.*;
import java.util.Map;

public class ColliderSimple extends Collider{

    public ColliderSimple(World world){
        super(world);
    }
    @Override
    public boolean canMove(Position pos) {
       return checkPos(pos) && checkCollision(pos);
    }

    @Override
    public boolean checkPos(Position pos){
       return pos.getX() > 0 && pos.getX() <= super.getWidht() && pos.getY() > 0 && pos.getY() <= super.getHeight();
    }


    @Override
    public boolean checkCollision(Position pos) { //Va parcourir les entités du monde
        ObservableMap<Position, Element> elements = this.getWorld().getElements();
        Rectangle rBird = new Rectangle((int)pos.getX(),(int)pos.getY(),20,20); // taille de l'oiseau à la place du 10
        for (Map.Entry<Position, Element> entry : elements.entrySet()){
            Element element = entry.getValue();
            if (element instanceof Obstacle){
                Position elemPos = entry.getKey();
                Rectangle rPipe = new Rectangle((int)elemPos.getX(),(int)elemPos.getY(),element.getWidth(),element.getHeight());
                if (rBird.intersects(rPipe)){
                    return false;
                }
            }
        }
        return true;
    }
}
