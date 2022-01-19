package Persistance;

import model.Player;


import java.io.*;
import java.util.List;

/**
 * Classe pour la sauvegarde de nos données
 */
public class SaverBinaire implements Save {

    private final String filePath;

    /**
     * Constructeur de la classe
     *
     * @param filePath chemin d'accès du fichier de persistance
     */
    public SaverBinaire(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Méthode qui sauvegarde nos données
     *
     * @param players liste de joueurs
     */
    @Override
    public void saveData(List<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (Player player : players) {
                oos.writeUTF(player.getPseudo());
                oos.writeInt(player.getScoreMax());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
