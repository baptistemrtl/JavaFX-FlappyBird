package model.game.manager;

import launcher.Launch;
import model.game.element.Element;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

    public void initializeGame(Button restartButton, Button homeButton) {
        initNodes(restartButton,homeButton);
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
            }
        });
    }

    private void initNodes(Button restartButton, Button homeButton){
        restartButton.setOnAction(e -> {
            if (Launch.getManager().isGameOver()) {
                //restart
            }
        });
        restartButton.opacityProperty().set(0);
        restartButton.disableProperty().set(true);
        homeButton.setOnAction(e -> {
                if (Launch.getManager().isGameOver()) {
                    System.out.println("try");
                   // Launch.getNavigator().navigateTo("MainWindow",mainStage);
                }
        });
       homeButton.opacityProperty().set(0);
        homeButton.disableProperty().set(true);
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
