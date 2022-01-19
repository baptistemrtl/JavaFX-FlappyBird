package model.game.manager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import model.Player;
import model.game.element.Element;
import model.game.renderer.Renderer;
import model.game.renderer.RendererSupplier;

import java.io.File;

public class FXControler {


    private Stage mainStage;
    private BorderPane gameBorderPane;
    private RendererSupplier rendererSupplier;
    private Renderer renderer;

    /**
     * Constructeur du FXController
     * @param pane layout actuel
     * @param stage Stage principal
     */
    public FXControler(BorderPane pane, Stage stage) {
        rendererSupplier = Renderer::new; //Instanciation d'un renderer grâce à l'interface fonctionelle
        gameBorderPane = pane;
        mainStage = stage;
    }

    /**
     * Méthode qui va initialiser les propriétés de la vue de jeu
     * en déléguant au renderer pour le binding et à la méthode initNodes pour initialiser
     * les propriétés des éléments de la vue (passés en paramètres)
     * @param restartButton
     * @param homeButton
     * @param scoreText
     */
    public void initializeGame(Button restartButton, Button homeButton, Text scoreText) {
        initNodes(restartButton,homeButton,scoreText); //initialisation des propriétés des Nodes
        renderer = getRendererSupplier().createRenderer(); //création d'un renderer
        Launch.getManager().createWorld(); //création d'un monde pour préparer au lancement du jeu
        renderer.renderWorld(gameBorderPane,Launch.getManager().getCurrentWorld()); //rendu de ce monde

        //Ajout d'un listener sur le monde du manager
        Launch.getManager().getCurrentWorld().getElements().addListener((ListChangeListener.Change<? extends Element> change)-> {
            while (change.next()) {
                //A l'ajout d'un élement à la liste d'Element du monde, on va l'afficher à la vue et régler son binding sur son ImageView
                for (Element obs : change.getAddedSubList()) {
                    renderer.renderImageView(gameBorderPane,obs);
                }
                //A la suppresion d'un élément de la liste d'Element du monde, on supprime le node associé à l'élément supprimé
                for (Element obs : change.getRemoved()){
                    for (Node leNode : gameBorderPane.getChildren()) {
                        if (leNode.getUserData() == obs) { //Possible grâce à Renderer -> renderImageView -> ligne 62
                            gameBorderPane.getChildren().remove(leNode);
                            break;
                        }
                    }
                }
            }

        });
    }

    /**
     * Initialisation des composants
     * @param restartButton
     * @param homeButton
     * @param scoreText
     */
    private void initNodes(Button restartButton, Button homeButton,Text scoreText) {
        //Initialisation du bouton de restart d'une partie
        restartButton.opacityProperty().set(0);
        restartButton.setViewOrder(0); //On le défini toujours au premier plan
        restartButton.setOnMouseClicked(e -> {
            //restart d'une partie
            if (Launch.getManager().isGameOver()) {
                Launch.getManager().restartGame();
                Launch.getManager().startBoucle();
            }
        });
        restartButton.disableProperty().set(true);

        //Initialisation du bouton de retour à la fenêtre principal
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
        scoreText.textProperty().bind(Launch.getManager().stringScoreProperty());//Binding du scoreText sur le scoreCourant
        //Ajout d'un listener sur la propriété gameOver du manager pour afficher les boutons home et restart à la mort de l'oiseau
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

    /**
     * Initalisation du scoreBoard et de l'affichage du Top3
     * @param first
     * @param second
     * @param third
     * @param scoreFirst
     * @param scoreSecond
     * @param scoreThird
     */
    public void initializeScoreBoard(Text first,Text second,Text third,Text scoreFirst,Text scoreSecond,Text scoreThird){
        switchBindValue(first,second,third,scoreFirst,scoreSecond,scoreThird,Launch.getManager().getLog().getPlayers().size());
        Launch.getManager().getLog().playersProperty().addListener((ListChangeListener<? super Player>) change->{
            while(change.next()){
                switchBindValue(first,second,third,scoreFirst,scoreSecond,scoreThird,Launch.getManager().getLog().getPlayers().size());
            }
        });

    }

    /**
     *Méthode qui permet de mettre à jour le binding du scoreboard
     */
    private void switchBindValue(Text first,Text second,Text third,Text scoreFirst,Text scoreSecond,Text scoreThird,int size){
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

    /**
     * On set le text du Node à ? si on ne connait valeur qu'il est censé contenir
     * @param node
     */
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
