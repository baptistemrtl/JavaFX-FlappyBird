package model.game.element;

public class Obstacle extends Element {

    public Obstacle (int width, int height, Position pos, String imageUrl) {
        super(width,height,pos,imageUrl);
    }

    public Obstacle(){
        super(50,100,new Position(400,0),"");
    }
}
