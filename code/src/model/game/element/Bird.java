package model.game.element;

/**
 * Oiseau du jeu
 */
public class Bird extends Element {

    /**
     * Constructeur de l'oiseau
     *
     * @param width    largeur de l'oiseau
     * @param height   hauteur de l'oiseau
     * @param pos      position de l'oiseau
     * @param imageUrl image de l'oiseau
     */
    public Bird (int width, int height, Position pos, String imageUrl) {
        super(width,height,pos,imageUrl);
    }
}
