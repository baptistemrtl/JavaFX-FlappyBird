package model.game.displacer;

import model.Position;
import model.game.World;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

public class BirdDisplacer extends Displacer {

    @Override
    public boolean move(World world, Element element) {
        Position pos = element.getPos();
        pos.setPosition(pos.getX()+1, pos.getY()-1);
        if(!Collider.isCollision(pos, world)) {
            element.setPos(pos);
            return true;
        }

        return false;
    }

    public boolean fly(World world, Bird bird) {
        Position pos = bird.getPos();
        pos.setPosition(pos.getX()+1, pos.getY()+5);
        if(!Collider.isCollision(pos, world)) {
            bird.setPos(pos);
            return true;
        }

        return false;
    }
}
