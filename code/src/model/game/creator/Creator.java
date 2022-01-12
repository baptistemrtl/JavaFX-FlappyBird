package model.game.creator;

import model.game.World.World;

public abstract class Creator {

    public abstract List<Element> createWorld();

    public abstract void createObstacle(List<Element> elements);
}
