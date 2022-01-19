package model.game.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe qui représente une position (x y) de manière
 * car tous les éléments utilisent un couple (x y) ce qui évite la redondance
 */
public class Position {

    //Propriété représentant l'abcisse
    private DoubleProperty x = new SimpleDoubleProperty();
        public double getX() { return x.get(); }
        public void setX(double x2) { x.set(x2); }
    public DoubleProperty xProperty() { return x; }

    //Propriété représentant l'ordonnée
    private DoubleProperty y = new SimpleDoubleProperty();
        public double getY() { return y.get(); }
        public void setY(double y2) { y.set(y2); }
    public DoubleProperty yProperty() { return y; }

    public Position(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Redéfinition de la méthode equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;

        return getX() == position.getX() && getY() == position.getY();
    }

    /**
     * Redéfinition de la méthode hashcode
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(getX());
        result = 31 * result + Double.hashCode(getY());

        return result;
    }
}
