package model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private DoubleProperty x = new SimpleDoubleProperty();
        public double getX(){ return x.get(); }
        public void setX(double x2){ x.set(x2); }
    public DoubleProperty xProperty(){ return x; }

    private DoubleProperty y = new SimpleDoubleProperty();
        public double getY(){ return y.get(); }
        public void setY(double y2){ y.set(y2); }
    public DoubleProperty yProperty(){ return y; }

    public Position(double x, double y) {
        setX(x);
        setY(y);
    }


    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Position pos = (Position) obj;
        return getX() == pos.getX() && getY() == pos.getY();
    }

    @Override // vue que plus treeMap (hashMap mtn) remplacer compareTo par HashCode
    public int compareTo(Position o) {
        if(getX() != o.getX()) {
            return getX() > o.getX() ? 1 : -1;
        }

        if (getY() != o.getY()) {
            return getY() > o.getY() ? 1 : -1;
        }

        return 0;
    }
}
