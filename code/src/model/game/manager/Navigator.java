package model.game.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * La classe Navigator permet de gérer les changements de vue.
 */
public class Navigator {

    private final Map<String, Scene> mapScenes = new HashMap<>();
    private Scene onUseScene;

    /**
     * Constructeur de la classe navigator
     * On instancie une map String,Scene pour se rediriger facilement
     *
     * @throws IOException the io exception
     */
    public Navigator() throws IOException {
        mapScenes.put("Game",new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Game.fxml")))));
        mapScenes.put("MainWindow",new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")))));
        mapScenes.put("ScoreBoard", new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/ScoreBoard.fxml")))));
        mapScenes.put("Error", new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Error.fxml")))));
        setDefaultScene();
    }

    /**
     * Méthode qui redirige en fonction d'un String passé en paramètres
     *
     * @param scene la vue à afficher
     * @param stage le stage de la vue
     */
    public void navigateTo(String scene, Stage stage) {
        Scene selectedScene = mapScenes.get(scene);
        if (selectedScene == null) {
            setDefaultScene(); //Par défaut, la scene est MainWindow
        } else {
            setOnUseScene(selectedScene);
        }
        //On affiche la scene
        onUseScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/background.css")).toExternalForm());
        stage.setScene(onUseScene);
        stage.show();
    }

    /**
     * Get la scene en cours d'utilisation
     *
     * @return la scene en cours d'utilisation
     */
    public Scene getOnUseScene() {
        if (onUseScene == null) {
            setDefaultScene();

            return getDefaultScene();
        }

        return onUseScene;
    }

    /**
     * Set la scene en cours d'utilisation
     *
     * @param onUseScene la scene en cours d'utilisation
     */
    public void setOnUseScene(Scene onUseScene) {
        this.onUseScene = onUseScene;
    }

    /**
     * Set la scene par défaut
     */
    private void setDefaultScene() {
        onUseScene = mapScenes.get("MainWindow");
    }

    /**
     * Get la scene par défaut
     *
     * @return la scene par défaut
     */
    private Scene getDefaultScene() {
        return mapScenes.get("MainWindow");
    }

}

