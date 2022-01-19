package model.game.logs;

import javafx.beans.property.ListProperty;

import model.Player;

import java.util.List;

/**
 * Interface qui va enregistrer nos utilisateurs et leur scoreMax
 * pour la persistance et l'utilisation de nos données
 */
public interface Log {

    /**
     * Ajout d'un joueur
     *
     * @param player le joueur à ajouter
     */
     void addPlayer(Player player);

    /**
     * ListProperty de players.
     *
     * @return La ListProperty
     */
     ListProperty<Player> playersProperty();

    /**
     * Get la liste des joueurs.
     *
     * @return la liste des joueurs
     */
     List<Player> getPlayers();

    /**
     * Cherche un joueur dans la liste des joueurs.
     *
     * @param pseudo le pseudo du joueur
     * @return un joueur
     */
    Player searchPlayer(String pseudo);
}
