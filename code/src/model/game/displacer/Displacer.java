package model.game.displacer;

import model.game.collider.Collider;
import model.game.element.Element;

public abstract class Displacer {

    private Collider collider;

    public Displacer(Collider collider) {
        this.collider = collider;
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public abstract boolean move(Element element);
}
