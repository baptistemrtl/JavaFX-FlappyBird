package model.game.displacer;

import model.Position;
import model.game.collider.Collider;
import model.game.element.Element;

public class ObstacleDisplacer extends Displacer {


    public ObstacleDisplacer(Collider collider) {
        super(collider);
    }

    @Override
    public boolean move(Element element) {
        Collider col = getCollider();
        if(col.checkCollision(col.getWorld().getCurrentBird().getPos())){
            Position pos = element.getPos();
            pos.setX(pos.getX()-5);
            element.setPos(pos);
            return true;
        }
        return false;
    }
}
