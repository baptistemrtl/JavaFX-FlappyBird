package view;

import model.Manager;
import model.Position;
import model.game.World;
import model.game.element.Bird;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class MainWindow {

    public void initialize() throws Exception{
        Manager man = new Manager();
        man.creerMonde();
        World world = man.getCurrentWorld();
        Bird currentBird = world.getCurrentBird();

        if (currentBird != null){
            ImageView elementBoy = new ImageView((Element) currentBird);
            elementBoy.setSize(currentBird.getWidth(),currentBird.getHeight());
        }
    }
}
