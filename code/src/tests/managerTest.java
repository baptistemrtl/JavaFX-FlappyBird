package tests;

import javafx.collections.ObservableList;
import model.game.manager.Manager;
import model.game.World.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

public class managerTest {

    public static void main(String[] args){
        Manager man = new Manager();
        World world = man.getCurrentWorld();
        ObservableList<Element> elements = world.getElements();
        for(Element elm : elements){
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

        man.startBoucle();

    }
}
