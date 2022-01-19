package model.game.logs;

import javafx.beans.property.*;
import model.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

/**
 * La classe LogSimple permet de gérer les joueurs dans le jeu.
 */
public class LogSimple implements Log{

    /**
     * Propriété représentant une liste de joueurs
     */
    private final ListProperty<Player> players = new SimpleListProperty<>(FXCollections.observableArrayList());
        public ObservableList<Player> getPlayers() { return players.get(); }
        private void setPlayers(ObservableList<Player> plyrs) {
            this.players.set(plyrs);
        }
    public ListProperty<Player> playersProperty() { return players; }

    /**
     * Constructeurs d'un LogSimple
     *
     * @param players la liste de joueurs
     */
    public LogSimple(ObservableList<Player> players) {
        setPlayers(players);
    }

    /**
     * Constructeur par défaut d'un LogSimple
     */
    public LogSimple() {
            setPlayers(FXCollections.observableArrayList());
    }

    /**
     * Recherche un joueur dans la liste
     *
     * @param pseudo Pseudo du joueur à chercher
     * @return une instance du Joueur s'il existe, null sinon
     */
    @Override
    public Player searchPlayer(String pseudo) {
        for (Player player : getPlayers()){
            if (player.getPseudo().equals(pseudo)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Ajout d'un joueur à la liste
     *
     * @param player Joueur à ajouter
     */
    @Override
    public void addPlayer(Player player) {
        players.add(player);
        players.sort(Comparator.comparing(Player::getScoreMax));
    }
}
