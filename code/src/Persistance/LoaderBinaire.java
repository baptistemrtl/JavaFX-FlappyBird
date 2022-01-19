package Persistance;

import model.Player;

import javafx.collections.FXCollections;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour la chargement de nos données
 */
public class LoaderBinaire implements Load {

    private final String filePath;

    /**
     * @param filePath chemin du fichier de persistance
     */
    public LoaderBinaire(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Méthode de chargement de nos données à partir d'un fichier
     * @return une liste de joueurs
     */
    @Override
    public List<Player> loadData() {
        List<Player> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            while (ois.available() > 0) {
                String pseudo = ois.readUTF();
                int scoreMax = ois.readInt();
                Player player = new Player(pseudo, scoreMax);
                players.add(player);
            }
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }

        return FXCollections.observableArrayList(players);
    }
}
