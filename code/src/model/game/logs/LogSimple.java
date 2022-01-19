package model.game.logs;

import model.Player;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogSimple extends Log{

    private final ListProperty<Player> players = new SimpleListProperty<>(FXCollections.observableArrayList());
        public ObservableList<Player> getPlayers() { return players.get(); }
        private void setPlayers(ObservableList<Player> players) { this.players.set(players); }
    public ReadOnlyListProperty<Player> playersProperty() { return players; }

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
