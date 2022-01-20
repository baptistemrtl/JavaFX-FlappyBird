package model.game.displacer;

import model.game.collider.Collider;
import model.game.element.Element;

/**
 * Classe qui va gérer le déplacement d'élément
 */
public abstract class Displacer {

    private Collider collider;
    private Boolean enableMove;

    /**
     * Constructeur
     *
     * @param collider le colisionneur
     */
    public Displacer(Collider collider) {
        this.collider = collider;
    }

    /**
     * Get le boolean enableMove
     *
     * @return le boolean
     */
    public Boolean isEnableMove() {
        return enableMove;
    }

    /**
     * Set enable move.
     *
     * @param bool la nouvelle valeur
     */
    public void setEnableMove(Boolean bool) {
        enableMove = bool;
    }

    /**
     * Get le colisionneur.
     *
     * @return le colisionneur
     */
    public Collider getCollider() {
        return collider;
    }

    /**
     * Set le collider.
     *
     * @param collider le nouveau collisionneur
     */
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    /**
     * Méthode qui va déplacer l'élément
     *
     * @param element un élément
     * @param move    une distance
     * @return un boolean
     */
    public abstract boolean move(Element element,Double move);
}
