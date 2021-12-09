package model;
import java.util.Objects;

public class Position implements Comparable{
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

    @Override
    public int compareTo(Object o) { // check comment bien implémenter la méthode compareTo (check les Ps de java de l'année dernière)
        return 0;
    }
}
