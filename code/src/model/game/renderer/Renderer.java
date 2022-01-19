package model.game.renderer;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import model.game.element.Position;
import model.game.World.World;
import model.game.element.Background;
import model.game.element.Bird;
import model.game.element.Element;
import model.game.element.Obstacle;

/**
 * Classe qui va ajouter les Element à la vue et gérer le binding entre Element et Node
 */
public class Renderer {

    /**
     * Méthode qui va déléguer pour mettre à jour le background et les éléments du monde
     * @param pane
     * @param world
     */
    public void renderWorld(BorderPane pane, World world) {
        renderBackground(pane);
        for (Element element : world.getElements()) {
            renderImageView(pane,element);
        }
    }

    /**
     * Méthode qui set le background du jeu si ce n'est pas déjà fait
     * @param gameBp
     */
    public void renderBackground(BorderPane gameBp) {
        for (Node node : gameBp.getChildren()) {
            if (node.getUserData() instanceof Background) {
                return;
            }
        }
        Background bg = new Background(450,700,new Position(0,0),"image/background2.png");
        ImageView background = new ImageView(bg.getImage());
        background.setViewOrder(1);
        background.setUserData(bg);
        background.setFitHeight(bg.getHeight());
        background.setFitWidth(bg.getWidth());
        background.setX(bg.getPos().getX());
        background.setY(bg.getPos().getY());
        gameBp.getChildren().add(0,background);
    }

    /**
     * Méthode qui va ajouter une ImageView d'un élément dans le layout
     * et bindles propriétés nécéssaires
     * @param gameBp
     * @param element
     */
    public void renderImageView(BorderPane gameBp,Element element) {
        ImageView elementIv = new ImageView();
        elementIv.setImage(new Image(element.getImage()));
        elementIv.setUserData(element); //On attache l'ImageView à l'élément pour la suppression dans le FXController
        elementIv.setFitWidth(element.getWidth());
        elementIv.setFitHeight(element.getHeight());
        //On fait en sorte que l'oiseau soit toujours sur un plan inférieur aux obstacles
        if (element instanceof Obstacle){
            elementIv.setViewOrder(0.50);
        }
        if (element instanceof Bird){
            elementIv.setViewOrder(0.60);
        }
        gameBp.getChildren().add(elementIv); //Ajout du Node au layout
        //Binding sur les propriétés x et y
        elementIv.layoutXProperty().bindBidirectional(element.getPos().xProperty());
        elementIv.layoutYProperty().bindBidirectional(element.getPos().yProperty());
    }
}
