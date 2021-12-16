package model;

// classe potentiellement innutile ! penser a check si pas delete
public class Vitesse {
    private double vitesseX;
    private double vitesseY;

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
