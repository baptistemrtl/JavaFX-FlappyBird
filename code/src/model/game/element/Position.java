package model.game.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe qui représente une position (x y) de manière
 * car tous les éléments utilisent un couple (x y) ce qui évite la redondance
 */
public class Position {

    //Propriété représentant l'abcisse
    private final DoubleProperty x = new SimpleDoubleProperty();

    /**
     * Gets le x
     *
     * @return son x
     */
    public double getX() { return x.get(); }

    /**
     * Set le x
     *
     * @param x2 sa nouvelle valeur
     */
    public void setX(double x2) { x.set(x2); }

    /**
     * La DoubleProperty représentant l'abscisse
     *
     * @return La DoubleProperty
     */
    public DoubleProperty xProperty() { return x; }

    //Propriété représentant l'ordonnée
    private final DoubleProperty y = new SimpleDoubleProperty();

    /**
     * Get le y
     *
     * @return son y
     */
    public double getY() { return y.get(); }

    /**
     * Set le y
     *
     * @param y2 sa nouvelle valeur
     */
    public void setY(double y2) { y.set(y2); }

    /**
     * La DoubleProperty représentant l'ordonnée
     *
     * @return La DoubleProperty
     */
    public DoubleProperty yProperty() { return y; }

    /**
     * Constructeur d'une position
     *
     * @param x l'abscisse
     * @param y l'ordonnée
     */
    public Position(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Set la position
     *
     * @param x sa nouvelle abscisse
     * @param y sa nouvelle ordonnée
     */
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Redéfinition de la méthode equals
     * @param o l'objet à comparer
     * @return true si les deux objets sont égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;

        return getX() == position.getX() && getY() == position.getY();
    }

    /**
     * Redéfinition de la méthode hashcode
     * @return le hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(getX());
        result = 31 * result + Double.hashCode(getY());

        return result;
    }
}
