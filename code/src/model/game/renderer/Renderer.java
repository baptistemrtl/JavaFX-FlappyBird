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

public class Renderer {

    public void renderWorld(BorderPane pane, World world) {
        renderBackground(pane);
        for (Element element : world.getElements()) {
            renderImageView(pane,element);
        }
    }

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

    public void renderImageView(BorderPane gameBp,Element element) {
        ImageView elementIv = new ImageView();
        elementIv.setImage(new Image(element.getImage()));
        elementIv.setUserData(element);
        elementIv.setFitWidth(element.getWidth());
        elementIv.setFitHeight(element.getHeight());
        elementIv.setViewOrder(0.5);
        gameBp.getChildren().add(elementIv);
        elementIv.layoutXProperty().bindBidirectional(element.getPos().xProperty());
        elementIv.layoutYProperty().bindBidirectional(element.getPos().yProperty());
    }
}
