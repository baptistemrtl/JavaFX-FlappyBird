package model.game.creator;

import model.game.element.Position;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui va créer un monde à partir d'un fichier
 */

public class CreatorSimple extends Creator {

    final String path = "rsrc/testFinishedWorlds/world1.txt";

    /**
     *  Méthode qui lit le fichier caractère par caractère pour créer une liste d'éléments
     *  On se limite ici à un monde fini
     * @return
     */
    @Override
    public List<Element> createWorld() {
        BufferedReader reader;
        double w=0.0, h=0.0;
        String line;
        List<Element> elements = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            while((line =  reader.readLine()) != null){
                for(char bloc : line.toCharArray()){
                    switch (bloc) {
                        case '1' -> {
                            elements.add(new Obstacle(50, 270, new Position(w, h), "image/down_pipe.png"));
                            w += 150;
                        }
                        case '3' -> {
                            elements.add(new Obstacle(50, 270, new Position(w,h+100), "image/up_pipe.png"));
                            w += 150;
                        }
                        default -> w += 50;
                    }
                }
                h+=50;
                w=0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return elements;
    }

    /*
    On ne se sert pas de cette méthode car cette classe faisait office
    de classe test pour le déplacement sur la vue d'Element
     */
    @Override
    public void createObstacle(List<Element> elements) {

    }
}
