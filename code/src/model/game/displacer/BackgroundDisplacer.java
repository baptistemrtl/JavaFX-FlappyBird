package model.game.displacer;

import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Element;

public class BackgroundDisplacer extends Displacer {

//    TODO: check use of this method
    public BackgroundDisplacer(Collider collider) {
        super(collider);
    }

    @Override
    public boolean move(Element element) {
        Position pos = element.getPos();
        pos.setX(pos.getX()-1);
        element.setPos(pos);
        return true;
    }
}
