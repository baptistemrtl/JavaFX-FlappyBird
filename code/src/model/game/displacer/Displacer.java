package model.game.displacer;

import model.game.World;
import model.game.element.Element;

public interface Displacer {
    boolean move(World world, Element element);
}
