package tests;
import javafx.collections.ObservableList;
import model.game.World.World;
import model.game.collider.Collider;
import model.game.collider.ColliderSimple;
import model.game.displacer.BirdDisplacer;
import model.game.displacer.Displacer;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;
import model.game.element.Position;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class globalTest {

    public static void main(String[] args) throws InterruptedException {
        // Déclaration des variables nécessaires
        Position deplacement = new Position(0,-200);
        Position start = new Position(1,1);
        World world = new World();
        world.addElement(new Obstacle(150,150,deplacement,""));
        Collider collider = new ColliderSimple(world);
        Displacer displacer = new BirdDisplacer(collider);
        Bird bird = new Bird(50,50,start,"");
        world.addElement(bird);

        //Verification de ce que contient le monde -> OK
        ObservableList<Element> elements = world.getElements();
        for (Element element : elements) {
            if (element instanceof Obstacle) {
                System.out.println("Obstacle -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
            if (element instanceof Bird) {
                System.out.println("Bird -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
        }

        //Test de deplacement de l'oiseau
        displacer.setEnableMove(true);
        displacer.move(bird,15.0);
        System.out.println("Bird 1er deplacement -> x :" + bird.getPos().getX() + " y :" + bird.getPos().getY());

        //Test de la collision
        int i = 1;
        boolean coll = displacer.move(bird,15.0);
        System.out.println(coll);
        while (coll){
            System.out.println("Bird" + i + " deplacement -> x :" + bird.getPos().getX() + " y :" + bird.getPos().getY());
            ++i;
            coll = displacer.move(bird,15.0);
            sleep(500);
        }

        World world1 = new World();
        elements = world1.getElements();
        for (Element element : elements) {
            if (element instanceof Bird) {
                System.out.println("Bird -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
            if (element instanceof Obstacle) {
                System.out.println("Obstacle -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
        }
        System.out.println("-------------------------");
        world1.restartWorld();
        for (Element element : world1.getElements()) {
            if (element instanceof Bird) {
                System.out.println("Bird -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
            if (element instanceof Obstacle) {
                System.out.println("Obstacle -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
        }

        System.out.println("-------------------------");
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        world1.addObstacles();
        for (Element element : world1.getElements()) {
            if (element instanceof Bird) {
                System.out.println("Bird -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
            if (element instanceof Obstacle) {
                System.out.println("Obstacle -> x :" + element.getPos().getX() + " y :" + element.getPos().getY());
            }
        }
    }
}
