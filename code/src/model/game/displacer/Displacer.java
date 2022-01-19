package model.game.displacer;

import model.game.collider.Collider;
import model.game.element.Element;

/**
 * Classe qui va gérer le déplacement d'élément
 */
public abstract class Displacer {

    private Collider collider;
    private Boolean enableMove;

    public Displacer(Collider collider) {
        this.collider = collider;
    }

    public Boolean isEnableMove() {
        return enableMove;
    }
    public void setEnableMove(Boolean bool) {
        enableMove = bool;
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public abstract boolean move(Element element,Double move);
}
