package model.game.creator;

import model.Position;
import model.game.World;
import model.game.element.Bird;
import model.game.element.Obstacle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui va créer un monde à partir d'un fichier
 * avec des 0 qui sont des blocs vides et 1 qui sont des morceaux de tuyau
 * On va se limiter à un monde fini au début et on aura une autre classe CreateurRandom
 * qui générera des blocs aléatoirement
 */

public class CreatorSimple extends Creator {

    String path;
    int width;
    int height;

    public CreatorSimple(String path) {
        this.path= path;
    }

    public World readWorldFile() {
        BufferedReader reader;
        double w=0.0, h=0.0;
        String line;
        World monde = new World();
        try{
            reader = new BufferedReader(new FileReader(path));
            while((line =  reader.readLine()) != null){
                for(char bloc : line.toCharArray()){
                    switch (bloc) {
                        case '1' -> {
                            monde.addElement(new Obstacle(50, 300, new Position(w, h), "image/down_pipe.png"));
                            w += 150;
                        }
                        case '3' -> {
                            monde.addElement(new Obstacle(50, 300, new Position(w, h), "image/up_pipe.png"));
                            w += 150;
                        }
                        case '2' -> monde.addElement(new Bird(50, 50, new Position(w, h), "image/bird.png"));
                        default -> w += 50;
                    }
                }
                h+=50;
                w=0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monde;
    }
}
