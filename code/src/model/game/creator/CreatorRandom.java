package model.game.creator;

import model.Position;
import model.game.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorRandom extends Creator{

    private String DOWN_PIPE = "/image/down_pipe.png";
    private String UP_PIPE = "/image/up_pipe.png";
    private final int PIPE_WIDTH = 50;
    private final int MIN_PIPE_HEIGHT = 50;
    private final int pipeHeightDiff = 200;
    private final int pipeWidthDiff = 230;
    private final int numberOfInstance = 3;

    private final Random alea = new Random();

    private Double birdFirstX = 100.0;
    private Double birdFirstY = 100.0;
    private int birdFirstWidth = 50;
    private int birdFirstHeight = 50;
    private String birdImage = "image/bird.png";

    private final int windowWidth = 450;
    private final int windowHeight = 700;



    public List<Element> createObstacle(World world) {
        List<Element> list = new ArrayList<Element>();
        int nb = world.getNumberOfObstacle();
        Obstacle last = new Obstacle();
        if (nb > 0){
            last = world.getLastObstacle();
            /*
            int randomHeight = (int) (Math.random() * (windowHeight-MIN_PIPE_HEIGHT-pipeHeightDiff));
            Obstacle upNew = new Obstacle(PIPE_WIDTH, randomHeight,new Position(last.getPos().getX()+last.getWidth() + pipeWidthDiff, windowHeight), "image/up_pipe.png");
            Obstacle downNew = new Obstacle(PIPE_WIDTH,windowHeight-upNew.getHeight()-pipeHeightDiff,new Position(upNew.getPos().getX(),0), "image/down_pipe.png");
            world.addElement(upNew);
            world.addElement(downNew);
            return world;
            */
        }
        else{
            Obstacle obs = new Obstacle(PIPE_WIDTH,MIN_PIPE_HEIGHT,new Position(200,-100),"image/down_pipe.png");
            last = obs;
            world.addElement(obs);
            list.add(obs);
            return list;
        }
        int randomHeight = (int) (Math.random() * (windowHeight-MIN_PIPE_HEIGHT-pipeHeightDiff));
        Obstacle downNew = new Obstacle(PIPE_WIDTH,randomHeight,new Position(last.getPos().getX()+last.getWidth() + pipeWidthDiff,0), "image/down_pipe.png");
        Obstacle upNew = new Obstacle(PIPE_WIDTH, windowHeight-downNew.getHeight()-pipeHeightDiff,new Position(downNew.getPos().getX(), downNew.getHeight() + pipeHeightDiff), "image/up_pipe.png");
        world.addElement(upNew);
        world.addElement(downNew);
        list.add(downNew);
        list.add(upNew);
        return list;

    }

    @Override
    public World createWorld() {
        World world = new World();
        world.addElement(new Bird(birdFirstWidth,birdFirstHeight, new Position(birdFirstX,birdFirstY),birdImage));
        for (int i = 0; i < numberOfInstance ; ++i){
            List<Element> list = createObstacle(world);
            world.addListElement(list);
        }

        return world;
    }
}
