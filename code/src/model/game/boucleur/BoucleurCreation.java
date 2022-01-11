package model.game.boucleur;

import launcher.Launch;
import model.game.creator.Creator;
import model.game.creator.CreatorRandom;
import model.game.element.Element;

public class BoucleurCreation extends Boucleur{
    private Creator creator = new CreatorRandom();

    @Override
    public void run() {
        while(isRunning()) {
            beep();
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e){
                setRunning(false);
            }
        }
    }

    @Override
    public void beep() {
        for (Element elm : Launch.getManager().getCurrentWorld().getElements()){
            if (elm.getPos().getX() < 0 ){
                creator.createObstacle(Launch.getManager().getCurrentWorld());
                Launch.getManager().getCurrentWorld().delElement(elm);
                System.out.println("crea");
                break;
            }
        }
    }
}
