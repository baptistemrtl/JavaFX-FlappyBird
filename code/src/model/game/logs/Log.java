package model.game.logs;

import model.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Log {

    private List<Player> players = new ArrayList<>();

    public abstract void addPlayer(Player player);

    public abstract List<Player> getPlayers();

    public abstract boolean logged(Player player);
}
