package model.game.renderer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Classe qui va jouer un audio
 */
public class SoundPlayer implements ISound{

    private MediaPlayer mediaPlayer;

    /**
     * Constructeur du SoundPlayer
     *
     * @param filepath l'emplacement du fichier audio
     */
    public SoundPlayer(String filepath) {
        mediaPlayer = new MediaPlayer(new Media(new File(filepath).toURI().toString()));
    }

    @Override
    public void play() {
        mediaPlayer.play();
    }

    /**
     * Changement de l'audio à jouer
     *
     * @param filepath le nouvel emplacement du fichier audio
     */
    public void setFilePath(String filepath){
        mediaPlayer = new MediaPlayer(new Media(new File(filepath).toURI().toString()));
    }
}
