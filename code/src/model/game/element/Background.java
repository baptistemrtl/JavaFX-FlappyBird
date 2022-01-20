package model.game.element;

/**
 * Background de jeu
 */
public class Background extends Element {

    /**
     * Constructeur de Background
     *
     * @param width    sa largeur
     * @param height   sa hauteur
     * @param pos      sa position
     * @param imageUrl son image
     */
    public Background(int width, int height, Position pos, String imageUrl) {
        super(width, height, pos, imageUrl);
    }
}
