package model.game.logs;

import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Log {

    private final List<Player> players = new ArrayList<>();

    public abstract void addPlayer(Player player);

    public abstract void setPlayers(ObservableList<Player> players);

    public abstract ListProperty<Player> playersProperty();

    public abstract void sort(ObservableList<Player> plyrs);

    public abstract List<Player> getPlayers();

    public abstract boolean logged(Player player);

    public abstract Player searchPlayer(String pseudo);
}
