package Persistance;

import model.Player;
import java.util.List;

/**
 * Interface pour la sauvegarde de données
 */
public interface Save {

    /**
     * Sauvegarde les données dans un fichier
     *
     * @param players Liste des joueurs
     */
    void saveData(List<Player> players);

}
