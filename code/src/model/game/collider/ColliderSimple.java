package model.game.collider;

import javafx.collections.ObservableList;

import model.game.element.Position;
import model.game.World.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

public class ColliderSimple extends Collider{

    public ColliderSimple(World world) {
        super(world);
    }
    @Override
    public boolean canMove(Position pos) {
       return checkCollision(pos) && checkPos(pos);
    }

    @Override
    public boolean checkPos(Position pos){
       return pos.getY() >= 0 && pos.getY() <= 700;
    }


    @Override
    public boolean checkCollision(Position pos) { // Va parcourir les entités du monde, Pos = position actuelle de l'oiseau

        ObservableList<Element> elements = this.getWorld().getElements();
        Bird bird = getWorld().getCurrentBird();


       /* Rectangle rBird = new Rectangle((int)pos.getX(),(int)pos.getY(),bird.getWidth(),bird.getHeight());
        for (Map.Entry<Position, Element> entry : elements.entrySet()){
            Element element = entry.getValue();
            if (element instanceof Obstacle){
                Position elemPos = entry.getKey();
                Rectangle rPipe = new Rectangle((int)elemPos.getX(),(int)elemPos.getY(),element.getWidth(),element.getHeight());
                if (rBird.intersects(rBird.getX(),rBird.getY(),rBird.getWidth(),rBird.getHeight())){
                    return false;
                }
            }
        }
        return true;*/


        for (Element elm : elements){
            if (elm instanceof Obstacle){
                if (bird.getPos().getX()+40 >= elm.getPos().getX() &&
                        bird.getPos().getX() <= elm.getPos().getX()+elm.getWidth()) {
                    if (bird.getPos().getY()+30 >= elm.getPos().getY() &&
                            bird.getPos().getY()+30 <= elm.getPos().getY()+elm.getHeight()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
