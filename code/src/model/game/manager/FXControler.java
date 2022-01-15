package model.game.manager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import launcher.Launch;
import model.game.element.Element;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

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
        for (Element elem : Launch.getManager().getCurrentWorld().getElements()){
            System.out.println(elem.getImage());
        }
        Launch.getManager().getCurrentWorld().getElements().addListener((ListChangeListener.Change<? extends Element> change)-> {
            while (change.next()) {
                for (Element obs : change.getAddedSubList()) {
                    renderer.renderImageView(gameBorderPane,obs);
                }
                for (Element obs : change.getRemoved()){
                    renderer.renderImageView(gameBorderPane,obs);
                    /*Iterator<Node> iterator = gameBorderPane.getChildren().iterator();
                    while (iterator.hasNext()) {
                        Node leNode = iterator.next();
                        if (leNode.getUserData() == obs) {
                            iterator.remove();
                        }
                    }*/
                }
            }
        });
    }

    private void initNodes(Button restartButton, Button homeButton,Text scoreText){
        restartButton.setOnAction(e -> {
            if (Launch.getManager().isGameOver()) {
               // Launch.getManager().restart();
            }
        });
        restartButton.opacityProperty().set(100);
        restartButton.disableProperty().set(false);
        homeButton.setOnAction(e -> {
                if (Launch.getManager().isGameOver()) {
                   Launch.getNavigator().navigateTo("MainWindow",mainStage);
                }
        });
       homeButton.opacityProperty().set(100);
       homeButton.disableProperty().set(false);
       scoreText.textProperty().bind(Launch.getManager().stringScoreProperty());
       scoreText.setX(150);
       scoreText.setFont(Font.loadFont("https://www.dafont.com/fr/flappybirdy.font",30.0));
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
