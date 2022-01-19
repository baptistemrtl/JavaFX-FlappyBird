package Persistance;

import model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un Stub pour remplacer un chargement de données
 */
public class Stub implements Load{

    /**
     * Chargement d'une liste de joueurs factice pour tester l'affichage
     * @return
     */
    @Override
    public List<Player> loadData() {
        List<Player> lp = new ArrayList<>();
        lp.add(new Player("stubPlayer1"));
        lp.add(new Player("stubPlayer2"));
        lp.add(new Player("stubPlayer3"));
        new SaverBinaire("save.bin").saveData(lp);
        return lp;
    }
}
