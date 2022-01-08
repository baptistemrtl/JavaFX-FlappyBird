package model.game.logs;

import model.Player;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class LogSimple extends Log{

    public ObservableList<Player> olPlayers = FXCollections.observableArrayList();
    public ListProperty<Player> players = new SimpleListProperty<>(olPlayers);
    public ReadOnlyListProperty<Player> playersProperty() {
        return players;
    }
    public ObservableList<Player> getPlayers() { return players.get(); }
    public void setMesUtilisateurs(ObservableList<Player> mesUtilisateurs) {
        this.players.set(mesUtilisateurs); }

    public LogSimple(ObservableList<Player> players) {
        setMesUtilisateurs(players);
    }

    public LogSimple(){
        setMesUtilisateurs(FXCollections.observableArrayList());
    }

    @Override
    public boolean logged(Player player) {  // Mieux
        return (players.contains(player));
    }

    @Override
    public Player searchPlayer(String pseudo) {
        for (Player player : getPlayers()){
            if (player.getPseudo() == pseudo){
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
