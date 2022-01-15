package tests;

import javafx.collections.ObservableList;
import model.game.animation.Animation;
import model.game.animation.AnimationBird;
import model.game.animation.AnimationObstacle;
import model.game.boucleur.Boucleur;
import model.game.boucleur.BoucleurBird;
import model.game.boucleur.BoucleurDrop;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.manager.Manager;
import model.game.World.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

public class managerTest {

    public static void main(String[] args){

        //Test

        Manager man = new Manager();

        /*for(Element elm : elements){
            if (elm instanceof Obstacle){
                System.out.println("Obstacle");
            }
            if (elm instanceof Bird){
                System.out.println("Bird");
            }
        }
        Bird currentBird = world.getCurrentBird();
        if (currentBird == null){
            System.out.println("Bird null");
        }
        else
            System.out.println("Bird -> " + currentBird.getPos().getX() + " " + currentBird.getPos().getY());

        man.startBoucle();*/

        man.createWorld();
        man.getCurrentWorld().addObstacles();
        man.getCurrentWorld().addObstacles();
        Collider coll = new ColliderSimple(man.getCurrentWorld());
        for (Element elem : coll.getWorld().getElements()){
            System.out.println(elem.getImage());
        }
        Displacer bird = new BirdDisplacer(coll);
        Displacer obs = new ObstacleDisplacer(coll);
        Boucleur birdBcl = new BoucleurBird();
        Boucleur obsBcl = new BoucleurObstacle();
        Boucleur drop = new BoucleurDrop();
        Animation birdAnim = new AnimationBird((BirdDisplacer) bird,coll,(BoucleurBird) birdBcl,(BoucleurDrop) drop);
        birdAnim.setCollider(coll);
        Animation obsAnim = new AnimationObstacle((ObstacleDisplacer) obs,coll,(BoucleurObstacle) obsBcl);
        obsAnim.setCollider(coll);

        //birdAnim.animate();
        obsAnim.animate();

    }
}
