package model.game.collider;

import jdk.jshell.spi.ExecutionControl;
import model.Position;
import model.game.World;

public class Collider {
    private World world;

    Collider(World world){
        this.world = world;
    }

    static public boolean isCollision(Position pos, World world) {
        return false;
    }

}
