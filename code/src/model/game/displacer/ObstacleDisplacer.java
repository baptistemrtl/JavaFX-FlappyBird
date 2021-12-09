package model.game.displacer;

import model.Position;
import model.game.World;
import model.game.element.Element;

public class ObstacleDisplacer implements Displacer {
    @Override
    public boolean move(World world, Element element) { // a potentiellement modifier, car pas meme principe que background
        Position pos = element.getPos();
        pos.setX(pos.getX()-1);
        element.setPos(pos);
        return true;
    }
}
