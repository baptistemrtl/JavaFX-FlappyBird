package model.game.manager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import launcher.Launch;
import model.game.element.Element;
import model.game.element.Obstacle;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FXControler {

    /*
    FX COMPONENTS
     */
    private Stage mainStage;
    private BorderPane gameBorderPane;

    /*
    Main members
    */
    private RendererSupplier rendererSupplier;
    private Renderer renderer;


    public FXControler(BorderPane pane, Stage stage) {
        rendererSupplier = Renderer::new;
        gameBorderPane = pane;
        mainStage = stage;
    }

    //METHODES

     /*
    View Management
     */

    /*
    Game Management
     */

    public void initializeGame(Button restartButton, Button homeButton, Text scoreText) {
        initNodes(restartButton,homeButton,scoreText);
        renderer = getRendererSupplier().createRenderer();
        Launch.getManager().createWorld();
        renderer.renderWorld(gameBorderPane,Launch.getManager().getCurrentWorld());

        Launch.getManager().getCurrentWorld().getElements().addListener((ListChangeListener.Change<? extends Element> change)-> {
            while (change.next()) {
                for (Element obs : change.getAddedSubList()) {
                    renderer.renderImageView(gameBorderPane,obs);
                }
                for (Element obs : change.getRemoved()){
                        Iterator<Node> iterator = gameBorderPane.getChildren().iterator();
                        while (iterator.hasNext()) {
                            Node leNode = iterator.next();
                            if (leNode.getUserData() == obs) {
                                gameBorderPane.getChildren().remove(leNode);
                                break;
                            }
                        }
                }
            }

        });
    }

    private void initNodes(Button restartButton, Button homeButton,Text scoreText){
        restartButton.opacityProperty().set(0);
        restartButton.setViewOrder(0);
        restartButton.setOnMouseClicked(e -> {
            if (Launch.getManager().isGameOver()){
                Launch.getManager().restartGame();
            }
        });
        restartButton.disableProperty().set(true);


       homeButton.opacityProperty().set(00);
        homeButton.setOnMouseClicked(e -> {
            if (Launch.getManager().isGameOver()) {
                Launch.getNavigator().navigateTo("MainWindow",mainStage);
            }
        });
       homeButton.disableProperty().set(true);


       scoreText.textProperty().bind(Launch.getManager().stringScoreProperty());

        Launch.getManager().gameOverProperty().addListener((ChangeListener<? super Boolean>)(observable, oldValue, newValue) -> {
            if(newValue) {
                Launch.getManager().stopBoucle();
                restartButton.opacityProperty().set(20);
                restartButton.disableProperty().set(false);
            }
            else{
                restartButton.opacityProperty().set(0);
                restartButton.disableProperty().set(true);
            }
        });
    }

    //Getters & Setters
    public Renderer getRenderer(){ return renderer; }
    public RendererSupplier getRendererSupplier() {
        return rendererSupplier;
    }
    public void setRendererSupplier(RendererSupplier newRendererSupplier) {
        rendererSupplier = newRendererSupplier;
    }
    public void setGameBorderPane(BorderPane gameBorderPane) {
        this.gameBorderPane = gameBorderPane;
    }
    public BorderPane getGameBorderPane() {
        return gameBorderPane;
    }
}
