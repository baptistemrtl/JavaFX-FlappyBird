package model.game.creator;

import model.game.World;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui va créerun monde à partir d'un fichier
 * avec des 0 qui sont des blocs vides et  1 qui sont des morceaux de tuyau
 * On va se limiter à un monde fini au début et on aura une autre classe CreateurRandom
 * qui générera des blocs aléatoirement
 */

public class CreatorSimple extends Creator{

    String path;
    int width;
    int height;

    public CreatorSimple(String path, int width, int height){
        this.path= path;
        this.width = width;
        this.height = height;
    }

    public int[][] readWorldFile(){
        BufferedReader reader;
        int w=0, h=0;
        String line;
        int[][] world = new int [height][width];
        try{
            reader = new BufferedReader(new FileReader(path));
            while((line =  reader.readLine()) != null){
                for(char bloc : line.toCharArray()){
                    world[h][w]= (int) bloc;
                    w++;
                }
                h++;
                w=0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return world;
    }

    @Override
    public void creerObstacle(World world) {

    }
}
