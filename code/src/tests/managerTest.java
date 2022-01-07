package tests;

import javafx.collections.ObservableMap;
import model.Manager;
import model.Position;
import model.game.World;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.Map;

public class managerTest {

    public static void main(String[] args){
        Manager man = new Manager();
        man.creerMonde();
        World world = man.getCurrentWorld();
        ObservableMap<Position, Element> elements = world.getElements();
        for(Map.Entry<Position, Element> entry : elements.entrySet()){
            Element elm = entry.getValue();
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
