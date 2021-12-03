package model;
import java.util.Objects;

public class Position {
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

    public void setPositionXY(double x, double y) {
        setX(x);
        setY(y);
    }
}
