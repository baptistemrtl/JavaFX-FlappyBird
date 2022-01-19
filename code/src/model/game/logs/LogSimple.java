package model.game.logs;

import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import model.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogSimple extends Log{

    /**
     * Propriété représentant une liste de joueurs
     */
    private final ListProperty<Player> players = new SimpleListProperty<>(FXCollections.observableArrayList());
        public ObservableList<Player> getPlayers() { return players.get(); }
        public void setPlayers(ObservableList<Player> plyrs) {
            sort(plyrs);
            this.players.set(plyrs);
        }
    public ListProperty<Player> playersProperty() { return players; }


    /**
     * Fonction qui check létat de la liste avant de la trier
     * @param plyrs
     */
    public void sort(ObservableList<Player> plyrs) {
        if (plyrs ==null || plyrs.isEmpty()){
            return;
        }
        quicksort(0, plyrs.size()-1,plyrs);
    }

    /**
     * Algorithme de tri rapide
     * @param low index du début de la liste
     * @param high index de la fin de liste
     * @param plyrs liste de joueurs
     */
    private void quicksort(int low, int high,ObservableList<Player> plyrs) {
        int i = low, j = high;
        Player pivot = plyrs.get(low + (high-low)/2);
        while (i <= j) {
            while (plyrs.get(i).getScoreMax() > pivot.getScoreMax()) {
                i++;
            }
            while (plyrs.get(i).getScoreMax() < pivot.getScoreMax()){
                j--;
            }
            if (i <= j) {
                exchange(i, j,plyrs);
                i++;
                j--;
            }
        }
        if (low < j)
            quicksort(low, j,plyrs);
        if (i < high)
            quicksort(i, high,plyrs);
    }

    /**
     * Permutation de valeur dans la liste de joueurs
     * @param i
     * @param j
     * @param plyrs
     */
    private void exchange(int i, int j,ObservableList<Player> plyrs) {
        Player temp = plyrs.get(i);
        plyrs.get(i).setScoreMax(plyrs.get(j).getScoreMax());
        plyrs.get(i).setPseudo(plyrs.get(j).getPseudo());
        plyrs.get(j).setScoreMax(temp.getScoreMax());
        plyrs.get(j).setPseudo(temp.getPseudo());
    }

    /**
     * Constructeurs d'un LogSimple
     */
    public LogSimple(ObservableList<Player> players) {
        setPlayers(players);
    }

    public LogSimple() {
            setPlayers(FXCollections.observableArrayList());
    }

    /**
     * Check si un joueur est déjà enregistré dans la liste
     * @param player
     * @return
     */
    @Override
    public boolean logged(Player player) {
        return (players.contains(player));
    }

    /**
     * Recherche un joueur dans la liste
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
     * @param player
     */
    @Override
    public void addPlayer(Player player) {
        players.add(player);
        sort(players);
    }
}
