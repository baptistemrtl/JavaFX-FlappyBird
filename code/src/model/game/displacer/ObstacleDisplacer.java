package model.game.displacer;

import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Element;

public class ObstacleDisplacer extends Displacer {

    public ObstacleDisplacer(Collider collider) {
        super(collider);
    }

    @Override
    public boolean move(Element element,Double move) {
        Collider col = getCollider();
        if(col.canMove(col.getWorld().getCurrentBird().getPos())) {
            Position pos = element.getPos();
            pos.setX(pos.getX()-move);
            element.setPos(pos);

            return true;
        }

        return false;
    }
}
