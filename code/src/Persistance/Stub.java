package Persistance;

import model.Player;

import java.util.ArrayList;
import java.util.List;

public class Stub implements Load{
    @Override
    public List<Player> loadData() {
        List<Player> lp = new ArrayList<>();
        lp.add(new Player("stubPlayer"));
        new SaverBinaire("save.bin").saveData(lp);
        return lp;
    }
}
