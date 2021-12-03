package model.displacer;


import model.Position;
import model.Vitesse;

import javafx.scene.image.*;

import java.awt.geom.Rectangle2D;

public class Displacer {
    private Image image;
    protected Position pos;
    protected Vitesse speed;
    protected int width;
    protected int height;

    Displacer(){
        pos.setPositionXY(0,0);
        speed.setVitesse(0,0);
    }

    Displacer(Position pos, Vitesse speed){
        this.pos = pos;
        this.speed = speed;
    }

    public int getWidth(){ return width; }

    public void setWidth(int width){ this.width = width; }

    public int getHeight(){ return height; }

    public void setHeight(int height){ this.height = height; }

    public void setImage(Image img){ image = img; }

    public void resizeImage(String url, int height, int width){
        Image resized = new Image(url,width, height, false, false);
        setImage(resized);
    }

    public void updatePos(double time){
        pos.setX(pos.getX() + speed.getVitesseX() * time);
        pos.setY(pos.getY() + speed.getVitesseY() * time);
    }

    public void speedUp(double vX, double vY){
        speed.setVitesseX(vX + speed.getVitesseX());
        speed.setVitesseX(vY + speed.getVitesseY());
    }

    public void getObjectFrontier(){

    }

}
