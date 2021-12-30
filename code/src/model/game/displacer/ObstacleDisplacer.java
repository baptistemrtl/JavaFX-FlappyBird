package model.game.displacer;

import model.Position;
import model.game.World;
import model.game.collider.Collider;
import model.game.element.Element;

public class ObstacleDisplacer extends Displacer {


    public ObstacleDisplacer(Collider collider) {
        super(collider);
    }

    @Override
    public boolean move(Element element) { // a potentiellement modifier, car pas meme principe que background
        Position pos = element.getPos();
        pos.setX(pos.getX()-5);
        element.setPos(pos);
        return true;
    }
}
