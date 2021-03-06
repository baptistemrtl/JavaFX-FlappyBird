package model.game.creator;

import model.game.element.Position;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui va créer un monde et le remplir d'obstacles aléatoirement
 */
public class CreatorRandom extends Creator{

    //Attributs concernants les paramètres de base d'un obstacle
    private String DOWN_PIPE = "image/down_pipe.png";
    private String UP_PIPE = "image/up_pipe.png";
    private final int PIPE_WIDTH = 50;
    private final int MIN_PIPE_HEIGHT = 50;
    private final int pipeHeightDiff = 200;
    private final int pipeWidthDiff = 300;

    //Attributs concernants les paramètres de base de l'oiseau
    private Double birdFirstX = 100.0;
    private Double birdFirstY = 350.0;
    private int birdFirstWidth = 50;
    private int birdFirstHeight = 50;
    private String birdImage = "image/bird.png";

    //Hauteur de base de la fenêtre de jeu
    private int windowHeight = 700;

    /**
     * Méthode qui va retourner l'Elément avec le X le plus grand d'une liste d'Element
     *
     * @param elements the elements
     * @return last element x
     */
    public Element getLastElementX(List<Element> elements) {
        int maxX = 0;
        int index = 0;

        if (elements.isEmpty()) {
            return null;
        }
        for (Element elm : elements) {
            if (elm.getPos().getX() > maxX) {
                maxX = (int) elm.getPos().getY();
                ++index;
            }
        }

        return elements.get(index-1);
    }

    /**
     * Méthode qui génère aléatoirement un couple d'obstacles {UP;DOWN}
     * en se basant sur le dernier obstacle créé
     *
     * @param elements Liste des nouveaux obstacles
     */
    public void createObstacle(List<Element> elements) {
        Element last = getLastElementX(elements); //Récupération de l'obstacle avec le x le plus élevé

        //Si celui est null, c'est-à-dire, généralement au lancement d'une partie, on crée le premier couple d'obstacle
        if (last == null) {
            last = new Obstacle(PIPE_WIDTH, MIN_PIPE_HEIGHT, new Position(200, 0), DOWN_PIPE);
            elements.add(last);
            last = new Obstacle(PIPE_WIDTH, MIN_PIPE_HEIGHT, new Position(200, 700), UP_PIPE);
            elements.add(last);
        }

        //Génération aléatoire de la hauteur de l'obstacle du couple en y=0
        int randomHeight = (int) (Math.random() * (windowHeight-MIN_PIPE_HEIGHT-pipeHeightDiff));

        //Instanciation et ajout des obstacles à la liste d'éléments
        Obstacle downNew = new Obstacle(PIPE_WIDTH, randomHeight,
                new Position(last.getPos().getX()+last.getWidth() + pipeWidthDiff, 0), DOWN_PIPE);
        Obstacle upNew = new Obstacle(PIPE_WIDTH, windowHeight-downNew.getHeight()-pipeHeightDiff,
                new Position(downNew.getPos().getX(), downNew.getHeight() + pipeHeightDiff), UP_PIPE);
        elements.add(downNew);
        elements.add(upNew);
    }

    /**
     * Méthode qui va instancier uniquement l'oiseau qui va être jouable
     * et l'ajouter à la liste d'éléments, qui sera celle du World actuel
     *
     * @return la liste d'éléments
     */
    @Override
    public List<Element> createWorld() {
        List<Element> list = new ArrayList<Element>();
        list.add(new Bird(birdFirstWidth,birdFirstHeight, new Position(birdFirstX,birdFirstY),birdImage));
        return list;
    }
}
