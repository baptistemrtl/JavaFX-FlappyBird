package model.game.element;

/**
 * Obstacle du jeu
 */
public class Obstacle extends Element {

    /**
     * Constructeur d'un obstacle
     *
     * @param width    la largeur de l'obstacle
     * @param height   la hauteur de l'obstacle
     * @param pos      la position de l'obstacle
     * @param imageUrl l'image de l'obstacle
     */
    public Obstacle (int width, int height, Position pos, String imageUrl) {
        super(width,height,pos,imageUrl);
    }

    /**
     * Constructeur par défaut d'un obstacle
     */
    public Obstacle(){
        super(50,100,new Position(400,0),"");
    }
}
