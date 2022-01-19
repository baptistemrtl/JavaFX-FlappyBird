package model.game.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Navigator {

    private final Map<String, Scene> mapScenes = new HashMap<>();
    private Scene onUseScene;

    /**
     * Constructeur de la classe navigator
     * On instancie une map<String,Scene> pour se rediriger facilement
     * @throws IOException
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
     * @param scene
     * @param stage
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

    public void setOnUseScene(Scene onUseScene) {
        this.onUseScene = onUseScene;
    }

    private void setDefaultScene() {
        onUseScene = mapScenes.get("MainWindow");
    }

    private Scene getDefaultScene() {
        return mapScenes.get("MainWindow");
    }

    public Scene getOnUseScene() {
        if (onUseScene == null) {
            setDefaultScene();

            return getDefaultScene();
        }

        return onUseScene;
    }
}

