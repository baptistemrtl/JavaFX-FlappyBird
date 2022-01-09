package model.game.displacer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import model.Position;
import model.game.collider.Collider;
import model.game.element.Bird;
import model.game.element.Element;

public class BirdDisplacer extends Displacer{

    private double velocityX;
    private double velocityY;

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
    public boolean move(Element element) {
        if (isEnableMove()){
            if (element instanceof Bird) {
                return fly((Bird) element);
            }
        }
       return false;
    }

    public boolean fly(Bird bird) {   // c'est les memes faudra check ça
        Position pos = bird.getPos();
        pos.setY(pos.getY()-10);
        Position other = new Position(pos.getX(),pos.getY());
        if(getCollider().canMove(other)) {
            bird.setPos(pos);
            return true;
        }

        return false;
    }

    public boolean drop(Bird bird){
        Position pos = bird.getPos();
        pos.setY(pos.getY()+5);
        if(getCollider().canMove(pos)) {
            bird.setPos(pos);
            return true;
        }

        return false;
    }

}
