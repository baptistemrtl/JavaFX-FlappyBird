package model.game.displacer;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.game.element.Position;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

public class BirdDisplacer extends Displacer{

    private double velocityX;
    private double velocityY;
    private Node birdIv;

    public BirdDisplacer(Collider collider) {
        super(collider);

    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void addVelocity(double x, double y) {
        this.velocityX += x;
        this.velocityY += y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void render(GraphicsContext gc,Bird bird) {
        Image image = new Image(bird.getImage(),bird.getWidth(),bird.getHeight(),false,false);
        gc.drawImage(image, bird.getPos().getX(),bird.getPos().getY());
    }

    @Override
    public boolean move(Element element,Double move) {
        if (isEnableMove()){
            if (element instanceof Bird) {
                return fly((Bird) element,move);
            }
        }
       return false;
    }

    public boolean fly(Bird bird,Double move) {   // c'est les memes faudra check ça
        Position pos = bird.getPos();       // Position other = new Position(pos.getX(),pos.getY());
        if(getCollider().canMove(pos)) {
            pos.setY(pos.getY()+move);
            bird.setPos(pos);

            return true;
        }
        return false;
    }

 //  Sert à check la collision meme quand on ne presse pas espace, sinon pas de collision en descente
    public boolean drop(Bird bird) {
        Position pos = bird.getPos();
        pos.setY(pos.getY()+10);
        if(getCollider().canMove(pos)) {
            bird.setPos(pos);

            return true;
        }

        return false;
    }
}
