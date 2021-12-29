package tests;
import model.*;
import model.game.*;


public class testDeplacement {

    public static void main(String args[]){
       Manager man = new Manager();
       man.creerMonde();
       World world = man.getCurrentWorld();
       System.out.println(world.getElements());
    }
}
