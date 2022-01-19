package model.game.collider;

import javafx.collections.ObservableList;

import model.game.element.Position;
import model.game.World.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

public class ColliderSimple extends Collider{

    /**
     * Redéfinition du constructeur
     * @param world
     */
    public ColliderSimple(World world) {
        super(world);
    }

    /**
     * Méthode appelée pour connaître la possibilité de déplacement de l'oiseau
     * @param pos Position de l'oiseau actuel
     * @return
     */
    @Override
    public boolean canMove(Position pos) {
       return checkCollision(pos) && checkPos(pos);
    }

    /**
     * Check si l'oiseau est toujours dans la fenêtre de jeu
     * @param pos
     * @return
     */
    @Override
    protected boolean checkPos(Position pos){
       return pos.getY() > 0 && pos.getY() < 700;
    }

    /**
     * Check si l'oiseau n'est pas en collision avec un obstacle
     * @param pos
     * @return
     */
    @Override
    protected boolean checkCollision(Position pos) { // Va parcourir les entités du monde, Pos = position actuelle de l'oiseau

        ObservableList<Element> elements = this.getWorld().getElements();
        Bird bird = getWorld().getCurrentBird();


        for (Element elm : elements){
            if (elm instanceof Obstacle){
                if (bird.getPos().getX()+40 >= elm.getPos().getX() &&
                        bird.getPos().getX() <= elm.getPos().getX()+elm.getWidth()) {
                    if (bird.getPos().getY()+30 >= elm.getPos().getY() &&
                            bird.getPos().getY()+30 <= elm.getPos().getY()+elm.getHeight()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
