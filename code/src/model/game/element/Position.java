package model.game.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Position {
    private DoubleProperty x = new SimpleDoubleProperty();
        public double getX() { return x.get(); }
        public void setX(double x2) { x.set(x2); }
    public DoubleProperty xProperty() { return x; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;

        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(getX());
        result = 31 * result + Double.hashCode(getY());

        return result;
    }
}
