package model.game.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe qui représente un Element de la vue
 */
public abstract class Element {
    private Position pos;
    private int width;
    private int height;

    //Propriété pour se bind sur le filepath de l'image de l'élément
    private final StringProperty image = new SimpleStringProperty();

    /**
     * Get l'image de l'élément
     *
     * @return son image
     */
    public String getImage() { return image.get(); }

    /**
     * Set l'image de l'élément
     *
     * @param img sa nouvelle image
     */
    public void setImage(String img) { image.set(img); }

    /**
     * ImageProperty de l'élément
     *
     * @return le StringProperty de l'image
     */
    public StringProperty imageProperty() { return image; }

    /**
     * Get la position de l'élément
     *
     * @return sa position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Set la position de l'élément
     *
     * @param pos sa nouvelle position
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Get la largeur de l'élément
     *
     * @return sa largeur
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set la largeur de l'élément
     *
     * @param width sa nouvelle largeur
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get la hauteur de l'élément
     *
     * @return sa hauteur
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set la hauteur de l'élément
     *
     * @param height sa nouvelle hauteur
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Constructeur de l'élément
     *
     * @param width    la largeur de l'élément
     * @param height   la hauteur de l'élément
     * @param pos      la position de l'élément
     * @param imageUrl l'image de l'élément
     */
    public Element(int width, int height, Position pos, String imageUrl) {
        setWidth(width);
        setHeight(height);
        setPos(pos);
        setImage(imageUrl);
    }

    /**
     * Redéfinition de la fonction equals pour comparer des éléments en fonctions de leur position
     * @param o l'élément à comparer
     * @return true si les éléments sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element element)) return false;

        return getWidth() == element.getWidth() && getHeight() == element.getHeight() &&
                getPos().equals(element.getPos()) && getImage().equals(element.getImage());
    }
}
