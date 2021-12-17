package model.game.displacer;

import model.game.World;
import model.game.collider.Collider;
import model.game.element.Element;

public abstract class Displacer {

    private Collider collider;

    public Displacer(Collider collider){
        this.collider = collider;
    }
    public Collider getCollider(){
        return collider;
    }
    public abstract boolean move(Element element);
}
