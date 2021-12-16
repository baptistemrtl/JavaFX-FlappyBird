package model;
import java.util.Objects;

public class Position implements Comparable<Position> {
    private double X;
    private double Y;

    Position(double x, double y) {
        X = x;
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
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
        if(X != o.X) {
            return X > o.X ? 1 : -1;
        }

        if (Y != o.Y) {
            return Y > o.Y ? 1 : -1;
        }

        return 0;
    }
}
