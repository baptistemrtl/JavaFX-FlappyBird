package model.game.logs;

import javafx.collections.ListChangeListener;
import model.Player;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogSimple extends Log{

    private final ListProperty<Player> players = new SimpleListProperty<>(FXCollections.observableArrayList());
        public ObservableList<Player> getPlayers() { return players.get(); }
        public void setPlayers(ObservableList<Player> plyrs) {
            sort(plyrs);
            for (Player p : plyrs){
                System.out.println(p.getPseudo() + " " + p.getScoreMax());
            }
            this.players.set(plyrs);
        }



    public void sort(ObservableList<Player> plyrs) {
        if (plyrs ==null || plyrs.isEmpty()){
            return;
        }
        quicksort(0, plyrs.size()-1,plyrs);
    }

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

    private void exchange(int i, int j,ObservableList<Player> plyrs) {
        Player temp = plyrs.get(i);
        plyrs.get(i).setScoreMax(plyrs.get(j).getScoreMax());
        plyrs.get(i).setPseudo(plyrs.get(j).getPseudo());
        plyrs.get(j).setScoreMax(temp.getScoreMax());
        plyrs.get(j).setPseudo(temp.getPseudo());
    }

    public ListProperty<Player> playersProperty() { return players; }

    public LogSimple(ObservableList<Player> players) {
        setPlayers(players);
    }

    public LogSimple() {
            setPlayers(FXCollections.observableArrayList());
    }

    @Override
    public boolean logged(Player player) {  // Mieux
        return (players.contains(player));
    }

    @Override
    public Player searchPlayer(String pseudo) {
        for (Player player : getPlayers()){
            if (player.getPseudo().equals(pseudo)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }
}
