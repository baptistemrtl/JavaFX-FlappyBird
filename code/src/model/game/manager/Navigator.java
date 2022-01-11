package model.game.manager;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import launcher.Launch;

import java.io.IOException;
import java.util.*;

public class Navigator {

    private Map<String, Scene> mapScenes = new HashMap<>();
    private Scene onUseScene;

    public Navigator() throws IOException {
        mapScenes.put("Game",new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Game.fxml")))));
        mapScenes.put("MainWindow",new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")))));
        mapScenes.put("ScoreBoard", new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/ScoreBoard.fxml")))));
        setDefaultScene();
    }

    public void navigateTo(String scene, Stage stage){
        Scene selectedScene = mapScenes.get(scene);
        if (selectedScene == null){
            setDefaultScene();
        }
        else{
            setOnUseScene(selectedScene);
            onUseScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/background.css")).toExternalForm());
        }
        stage.setScene(onUseScene);
        stage.setWidth(450);
        stage.setHeight(700);
        stage.show();
        if (scene == "Game"){
            onUseScene.setOnKeyPressed(keyEvent -> Launch.getManager().keyMove(keyEvent.getCode()));
            Launch.getManager().startBoucle();
        }
    }

    public void setOnUseScene(Scene onUseScene) {
        this.onUseScene = onUseScene;
    }

    private void setDefaultScene(){
        onUseScene = mapScenes.get("MainWindow");
    }

    private Scene getDefaultScene(){
        return mapScenes.get("MainWindow");
    }

    public Scene getOnUseScene() {
        if (onUseScene == null){
            setDefaultScene();
        }
        return onUseScene;
    }
}

