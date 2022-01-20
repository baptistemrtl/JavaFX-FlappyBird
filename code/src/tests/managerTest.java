package tests;

import model.game.animation.Animation;
import model.game.animation.AnimationBird;
import model.game.animation.AnimationObstacle;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.manager.Manager;
import model.game.element.Element;

public class managerTest {

    public static void main(String[] args) {

        //Test

        Manager man = new Manager();

        man.createWorld();
        man.getCurrentWorld().addObstacles();
        man.getCurrentWorld().addObstacles();
        Collider coll = new ColliderSimple(man.getCurrentWorld());
        for (Element elem : coll.getWorld().getElements()) {
            System.out.println(elem.getImage());
        }

        BirdDisplacer bird = new BirdDisplacer(coll);
        ObstacleDisplacer obs = new ObstacleDisplacer(coll);
        BoucleurBird birdBcl = new BoucleurBird();
        BoucleurObstacle obsBcl = new BoucleurObstacle();
        BoucleurDrop drop = new BoucleurDrop();
        Animation birdAnim = new AnimationBird(bird,coll, birdBcl, drop);
        birdAnim.setCollider(coll);
        Animation obsAnim = new AnimationObstacle(obs,coll, obsBcl);
        obsAnim.setCollider(coll);

        obsAnim.animate();    }
}
