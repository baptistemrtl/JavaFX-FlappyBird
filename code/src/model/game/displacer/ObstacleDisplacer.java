package model.game.displacer;

import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Element;

/**
 * Classe qui gère le déplacement des obstacles
 */
public class ObstacleDisplacer extends Displacer {

    /**
     * Redéfiniton du constructeur
     * @param collider
     */
    public ObstacleDisplacer(Collider collider) {
        super(collider);
    }

    /**
     * Redéfinition de la méthode de déplacement d'un obstacle
     * @param element Obstacle à faire bouger
     * @param move Valeur du déplacement
     * @return false si collision avec l'oiseau, false sinon
     */
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
