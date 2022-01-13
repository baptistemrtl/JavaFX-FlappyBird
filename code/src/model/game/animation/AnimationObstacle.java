package model.game.animation;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import launcher.Launch;
import model.game.boucleur.BoucleurObstacle;
import model.game.collider.Collider;
import model.game.displacer.Displacer;
import model.game.displacer.ObstacleDisplacer;
import model.game.element.Element;
import model.game.element.Obstacle;

public class AnimationObstacle extends Animation implements InvalidationListener {


    public AnimationObstacle(ObstacleDisplacer displacer, Collider coll, BoucleurObstacle boucleur) {
        super(displacer, coll, boucleur);
    }

    @Override
    public void animate() {
        System.out.println("animObs");
        boucleur.addListener(this);
        boucleur.setRunning(true);
    }

    @Override
    public void invalidated(Observable observable) {
        System.out.println("Obstacle anim");
        for (Element element : Launch.getManager().getCurrentWorld().getElements()) {
            if (element instanceof Obstacle) {
                if (!displacer.move(element,5.0)) {
                    stopAnimation();
                }
            }
        }
    }

    public void stopAnimation(){
        boucleur.setRunning(false);
        boucleur.removeListener(this);
    }
}
