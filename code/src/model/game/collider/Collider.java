package model.game.collider;

import model.game.element.Position;
import model.game.World.World;

/**
 * Classe qui va gérer les collisions entre l'oiseau et les obstacles
 */
public abstract class Collider {

    //Monde actuel
    private World world;

    /**
     * Get le monde.
     *
     * @return le monde
     */
    public World getWorld() {
        return world;
    }

    /**
     * set le monde.
     *
     * @param world le monde
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Le constructeur
     *
     * @param world le Monde
     */
    public Collider(World world) {
        this.world = world;
    }

    /**
     * Permet de savoir si l'élément peut être déplacé
     *
     * @param pos la position
     * @return boolean
     */
    public abstract boolean canMove(Position pos);

    /**
     * Check la position
     *
     * @param pos la position
     * @return un boolean
     */
    protected abstract boolean checkPos(Position pos);

    /**
     * Check si il y a une collision
     *
     * @param pos la position
     * @return un boolean
     */
    protected abstract boolean checkCollision(Position pos); //test

}
