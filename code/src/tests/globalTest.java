package tests;
import javafx.collections.ObservableMap;
import model.*;
import model.game.*;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.Map;


public class globalTest {

    public static void main(String args[]){
        // Déclaration des variables nécessaires
        Position deplacement = new Position(0,200);
        Position start = new Position(0,0);
        World world = new World();
        world.addElement(new Obstacle(150,150,deplacement,""));
        Collider collider = new ColliderSimple(world);
        Displacer displacer = new BirdDisplacer(collider);
        Bird bird = new Bird(50,50,start,"");
        world.addElement(bird);

        //Verification de ce que contient le monde -> OK
        ObservableMap<Position, Element> elements = world.getElements();
        for (Map.Entry<Position, Element> entry : elements.entrySet()){
            Element element = entry.getValue();
            if (element instanceof Obstacle){
                System.out.println("Obstacle -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
            if (element instanceof Bird){
                System.out.println("Bird -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
        }

        //Test de deplacement de l'oiseau
        displacer.move(bird);
        System.out.println("Bird 1er deplacement -> x :" + bird.getPos().getX() + " y :" + bird.getPos().getY());

        //Test de la collision
        int i = 1;
        boolean coll = displacer.move(bird);
        while (coll == true){
            System.out.println("Bird" + i + " deplacement -> x :" + bird.getPos().getX() + " y :" + bird.getPos().getY());
            ++i;
            coll = displacer.move(bird);
        }
    }
}
