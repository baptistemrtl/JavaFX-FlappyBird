package model.game.animation;

import model.game.boucleur.Boucleur;
import model.game.collider.Collider;
import model.game.displacer.Displacer;

/**
 * Classe abstraite représentant un listener d'un boucleur
 */
public abstract class Animation {

    protected Displacer displacer;
    protected Collider collider;
    protected Boucleur boucleur;

    /**
     * Constructeur de la classe qui ne va pas changer
     * @param displacer Déplaceur d'élément
     * @param coll Gérant des collisions
     * @param boucleur Boucleur écouté
     */
    public Animation(Displacer displacer,Collider coll,Boucleur boucleur) {
        this.displacer = displacer;
        this.boucleur = boucleur;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    /**
     * Méthode pour lancer le boucleur
     */
    public abstract void animate();

    /**
     * Méthode pour arrêter la boucle
     */
    public abstract void stopAnimation();

}
