package model.game.displacer;

import model.game.World;
import model.game.collider.Collider;
import model.game.element.Element;

public abstract class Displacer {

    protected Collider collider;
    public abstract boolean move(World world, Element element);
}
