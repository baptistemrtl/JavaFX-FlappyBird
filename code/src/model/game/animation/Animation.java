package model.game.animation;

import model.game.boucleur.Boucleur;
import model.game.collider.Collider;
import model.game.displacer.Displacer;

public abstract class Animation {

    protected Displacer displacer;
    protected Collider collider;
    protected Boucleur boucleur;

    public Animation(Displacer displacer,Collider coll,Boucleur boucleur) {
        this.displacer = displacer;
        this.boucleur = boucleur;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public abstract void animate();

    public abstract void stopAnimation();

}
