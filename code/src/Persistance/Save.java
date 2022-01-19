package Persistance;

import model.Player;
import java.util.List;

/**
 * Interface pour la sauvegarde de données
 */
public interface Save {

    void saveData(List<Player> players);

}
