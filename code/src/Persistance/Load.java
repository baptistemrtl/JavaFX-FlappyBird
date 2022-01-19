package Persistance;

import model.Player;
import java.util.List;

/**
 * Interface pour charger les données
 */
public interface Load {

    /**
     * Charge les données
     *
     * @return la liste des joueurs
     */
    List<Player> loadData();

}
