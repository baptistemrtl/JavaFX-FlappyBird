package model.game.creator;

import model.game.element.Position;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorRandom extends Creator{

    private String DOWN_PIPE = "image/down_pipe.png";
    private String UP_PIPE = "image/up_pipe.png";
    private final int PIPE_WIDTH = 50;
    private final int MIN_PIPE_HEIGHT = 50;
    private final int pipeHeightDiff = 200;
    private final int pipeWidthDiff = 300;
    private final int numberOfInstance = 3;

    private final Random alea = new Random();

    private Double birdFirstX = 100.0;
    private Double birdFirstY = 350.0;
    private int birdFirstWidth = 50;
    private int birdFirstHeight = 50;
    private String birdImage = "image/bird.png";

    private final int windowWidth = 450;
    private final int windowHeight = 700;

    public Element getLastElementX(List<Element> elements) {
        int maxX = 0;
        int index = 0;

        if (elements.isEmpty()) {
            return null;
        }
        for (Element elm : elements) {
            if (elm.getPos().getX() > maxX) {
                maxX = (int) elm.getPos().getY();
                ++index;
            }
        }

        return elements.get(index-1);
    }

    public void createObstacle(List<Element> elements) {
        Element last = getLastElementX(elements);
        if (last == null) {
            last = new Obstacle(PIPE_WIDTH, MIN_PIPE_HEIGHT, new Position(200, 0), DOWN_PIPE);
            elements.add(last);
            last = new Obstacle(PIPE_WIDTH, MIN_PIPE_HEIGHT, new Position(200, 700), UP_PIPE);
            elements.add(last);
        }

        int randomHeight = (int) (Math.random() * (windowHeight-MIN_PIPE_HEIGHT-pipeHeightDiff));
        Obstacle downNew = new Obstacle(PIPE_WIDTH, randomHeight,
                new Position(last.getPos().getX()+last.getWidth() + pipeWidthDiff, 0), DOWN_PIPE);
        Obstacle upNew = new Obstacle(PIPE_WIDTH, windowHeight-downNew.getHeight()-pipeHeightDiff,
                new Position(downNew.getPos().getX(), downNew.getHeight() + pipeHeightDiff), UP_PIPE);
        elements.add(downNew);
        elements.add(upNew);
    }

    @Override
    public List<Element> createWorld() {
        List<Element> list = new ArrayList<Element>();
        list.add(new Bird(birdFirstWidth,birdFirstHeight, new Position(birdFirstX,birdFirstY),birdImage));
        return list;
    }
}
