package model.game.displacer;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

/**
 * Classe qui va gérer le déplacement d'un oiseau
 */
public class BirdDisplacer extends Displacer{

    private double velocityX;
    private double velocityY;

    /**
     * Redéfinition du constructeur
     * @param collider
     */
    public BirdDisplacer(Collider collider) {
        super(collider);

    }

    /*
    Tentative d'implémentation d'une vitesse pour faire accéléer
     */
    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
    public void addVelocity(double x, double y) {
        this.velocityX += x;
        this.velocityY += y;
    }
    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Méthode qui va déléguer pour déplacer l'oiseau
     * @param element Oiseau actuelle
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

