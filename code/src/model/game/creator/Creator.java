package model.game.creator;

import model.game.World.World;
import model.game.element.Element;

import java.util.List;

public abstract class Creator {

    public abstract World createWorld();

    public abstract void createObstacle(World world);
}
