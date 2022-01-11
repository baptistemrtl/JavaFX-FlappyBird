package model.game.manager;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import launcher.Launch;
import model.game.World.World;
import model.game.element.Element;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

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


    public FXControler(BorderPane pane, Stage stage){
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

    public void initializeGame(Button restartButton, Button homeButton){
        initNodes(restartButton,homeButton);
        renderer = getRendererSupplier().createRenderer();
        if (Launch.getManager().getCurrentWorld() == null){
            Launch.getManager().createWorld();
        }
        renderer.renderWorld(gameBorderPane,Launch.getManager().getCurrentWorld());

        Launch.getManager().getCurrentWorld().getElements().addListener((ListChangeListener.Change<? extends Element> change)-> {
            while (change.next()) {
                for (Element elm : change.getAddedSubList()) {
                    Launch.getManager().getCurrentWorld().addElement(elm);
                    renderer.renderWorld(gameBorderPane,Launch.getManager().getCurrentWorld());
                }
                for (Element elm : change.getRemoved()) {
                    gameBorderPane.getChildren().removeIf(leNode -> leNode.getUserData() == elm);
                }
            }
            renderer.renderWorld(gameBorderPane,Launch.getManager().getCurrentWorld());

        });
        Launch.getManager().startBoucle();
    }

    private void initNodes(Button restartButton, Button homeButton){
        restartButton.setOnAction(e -> {
            if (Launch.getManager().isGameOver()){
                //restart
            }
        });
        restartButton.opacityProperty().set(0);
        restartButton.disableProperty().set(true);
        homeButton.setOnAction(e -> {
                if (Launch.getManager().isGameOver()){
                    //stop
                    Launch.getNavigator().navigateTo("MainWindow",mainStage);
                }
        });
        homeButton.opacityProperty().set(0);
        homeButton.disableProperty().set(true);
    }

    //Getters & Setters

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
