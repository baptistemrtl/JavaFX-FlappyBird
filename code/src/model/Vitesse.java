package model;

public class Vitesse {
    private double vitesseX = 0;
    private double vitesseY = 0;

    Vitesse(double x, double y){
        vitesseX = x;
        vitesseY = y;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY(){
        return vitesseY;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public void setVitesse(double x,double y){
        setVitesseY(y);
        setVitesseX(x);
    }

}
