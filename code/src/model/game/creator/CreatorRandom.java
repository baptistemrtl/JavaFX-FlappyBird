package model.game.creator;

import model.Position;
import model.game.World;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.Random;

public class CreatorRandom extends Creator{

    private String DOWN_PIPE = "/image/down_pipe.png";
    private String UP_PIPE = "/image/up_pipe.png";
    private final int PIPE_WIDTH = 40;
    private final int PIPE_HEIGHT = 180;
    private Random alea = new Random();

    public Obstacle creerObstacle2(World world, Position pos){
        int obstacleGap = alea.nextInt();

        Element up_pipe = new Obstacle(PIPE_WIDTH,PIPE_HEIGHT, pos,UP_PIPE);
        Element down_pipe = new Obstacle(PIPE_WIDTH,PIPE_HEIGHT, pos,DOWN_PIPE);
        return null;
    }

    private void downPosition(Position pos){
        pos.setX(pos.getX()+40);
        pos.setY(pos.getY() );
    }


    public void creerObstacle(World world) {

    }
}
