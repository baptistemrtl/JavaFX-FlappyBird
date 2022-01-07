package model.game.displacer;

import model.Position;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

public class BirdDisplacer extends Displacer {

    private final int A = 1;
    private final int B = -2;
    private final int C = -3;

    public BirdDisplacer(Collider collider) {
        super(collider);
    }

    @Override
    public boolean move(Element element) {
       if (element instanceof Bird) {
           return fly((Bird) element);
       }
       return false;
    }

    public boolean fly(Bird bird) {   // c'est les memes faudra check ça
        Position pos = bird.getPos();
        pos.setY(pos.getY()+10);
        if(getCollider().canMove(pos)) {
            bird.setPos(pos);
            return true;
        }

        return false;
    }

    public boolean drop(Bird bird){
        Position pos = bird.getPos();
        pos.setY(pos.getY()-10);
        if(getCollider().canMove(pos)) {
            bird.setPos(pos);
            return true;
        }

        return false;
    }
}
