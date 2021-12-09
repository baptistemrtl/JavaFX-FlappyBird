package model.game.displacer;

import model.Position;
import model.game.World;
import model.game.element.Element;

public class BackgroundDisplacer implements Displacer {
    @Override
    public boolean move(World world, Element element) {
        Position pos = element.getPos();
        pos.setX(pos.getX()-1);
        element.setPos(pos);
        return true;
    }
}
