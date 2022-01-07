package Persistance;

import model.Player;

import javafx.collections.FXCollections;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoaderBinaire implements Load {

    private final String filePath;

    public LoaderBinaire(String filePath) {
        this.filePath = filePath;
    }

    public LoaderBinaire() {
        this("");
    }

    @Override
    public List<Player> loadData() {
        List<Player> players = new ArrayList<>();
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                throw new Exception("Le fichier n'existe pas");
            }

            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream buff = new BufferedInputStream(fis);
            ObjectInputStream o = new ObjectInputStream(buff);
            String pseudo = (String)o.readObject();

            while(pseudo!=null) { //tant qu'il y a quelque chose à lire
                //String pseudo= (String)o.readObject();
                String motDePAsse=(String) o.readObject();
                int scoreMax = (int)o.readObject();
                Player player = new Player(pseudo,scoreMax);
                players.add(player);
                pseudo = (String)o.readObject();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(players);
    }
}
