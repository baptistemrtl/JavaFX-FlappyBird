package model.game.manager;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import launcher.Launch;
import model.game.element.Element;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

import java.io.File;

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
                    for (Node leNode : gameBorderPane.getChildren()) {
                        if (leNode.getUserData() == obs) {
                            gameBorderPane.getChildren().remove(leNode);
                            break;
                        }
                    }
                }
            }

        });
    }

    private void initNodes(Button restartButton, Button homeButton,Text scoreText) {
        restartButton.opacityProperty().set(0);
        restartButton.setViewOrder(0);
        restartButton.setOnMouseClicked(e -> {
            if (Launch.getManager().isGameOver()) {
                Launch.getManager().restartGame();
                Launch.getManager().startBoucle();
            }
        });
        restartButton.disableProperty().set(true);


       homeButton.opacityProperty().set(0);
        homeButton.setOnMouseClicked(e -> {
            if (Launch.getManager().isGameOver()) {
                Launch.getNavigator().navigateTo("MainWindow",mainStage);
                Launch.getManager().stopBoucle();
                Launch.getManager().restartGame();
                homeButton.opacityProperty().set(0);
                homeButton.disableProperty().set(true);
                restartButton.opacityProperty().set(0);
                restartButton.disableProperty().set(true);
            }
        });
       homeButton.disableProperty().set(true);


       scoreText.textProperty().bind(Launch.getManager().stringScoreProperty());

        Launch.getManager().gameOverProperty().addListener((ChangeListener<? super Boolean>)(observable, oldValue, newValue) -> {
            if(newValue) {
                Launch.getManager().stopBoucle();
                restartButton.opacityProperty().set(20);
                restartButton.disableProperty().set(false);
                homeButton.opacityProperty().set(20);
                homeButton.disableProperty().set(false);
            } else {
                restartButton.opacityProperty().set(0);
                restartButton.disableProperty().set(true);
                homeButton.opacityProperty().set(0);
                homeButton.disableProperty().set(true);
            }
        });
    }

    public void initializeScoreBoard(Text first,Text second,Text third,Text scoreFirst,Text scoreSecond,Text scoreThird){
        int size = Launch.getManager().getLog().getPlayers().size();
        switch (size){
            case 0:
                setDefaultText(first);
                setDefaultText(second);
                setDefaultText(third);
                setDefaultText(scoreFirst);
                setDefaultText(scoreSecond);
                setDefaultText(scoreThird);
                break;
            case 1:
                first.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).pseudoProperty());
                scoreFirst.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).scoreProperty().asString());
                setDefaultText(second);
                setDefaultText(third);
                setDefaultText(scoreSecond);
                setDefaultText(scoreThird);
                break;
            case 2:
                first.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).pseudoProperty());
                scoreFirst.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).scoreProperty().asString());
                second.textProperty().bind(Launch.getManager().getLog().getPlayers().get(1).pseudoProperty());
                scoreSecond.textProperty().bind(Launch.getManager().getLog().getPlayers().get(1).scoreProperty().asString());
                setDefaultText(third);
                setDefaultText(scoreThird);
                break;
            default:
                first.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).pseudoProperty());
                scoreFirst.textProperty().bind(Launch.getManager().getLog().getPlayers().get(0).scoreProperty().asString());
                second.textProperty().bind(Launch.getManager().getLog().getPlayers().get(1).pseudoProperty());
                scoreSecond.textProperty().bind(Launch.getManager().getLog().getPlayers().get(1).scoreProperty().asString());
                third.textProperty().bind(Launch.getManager().getLog().getPlayers().get(2).pseudoProperty());
                scoreThird.textProperty().bind(Launch.getManager().getLog().getPlayers().get(2).scoreProperty().asString());
                break;
        }
    }

    private void setDefaultText(Text node){
        node.textProperty().set("?");
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
