package model.game.displacer;

import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

/**
 * Classe qui va gérer le déplacement d'un oiseau
 */
public class BirdDisplacer extends Displacer{

    /**
     * Redéfinition du constructeur
     *
     * @param collider le colisionneur
     */
    public BirdDisplacer(Collider collider) {
        super(collider);

    }

    /**
     * Méthode qui va déléguer pour déplacer l'oiseau
     *
     * @param element Oiseau actuel
     * @param move Valeur du déplacement de l'oiseau
     * @return true si non-collision, false sinon
     */
    @Override
    public boolean move(Element element,Double move) {
        if (isEnableMove()){
            if (element instanceof Bird) {
                return displace((Bird) element,move);
            }
        }
       return false;
    }

    /**
     * Méthode qui va faire déplacer l'oiseau
     *
     * @param bird Oiseau à faire bouger
     * @param move Valeur du déplacement
     * @return la possibilité de déplacement
     */
    private boolean displace(Bird bird,Double move) {
        Position pos = bird.getPos();
        if(getCollider().canMove(pos)) {
            pos.setY(pos.getY()+move);
            bird.setPos(pos);
            return true;
        }
        return false;
    }
}

