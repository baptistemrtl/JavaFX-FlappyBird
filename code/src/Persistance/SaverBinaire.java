package Persistance;

import model.Player;


import java.io.*;
import java.util.List;

public class SaverBinaire implements Save {

    private final String filePath;

    public SaverBinaire(String filePath) {
        this.filePath = filePath;
    }

    public SaverBinaire() {
        this("");
    }

    @Override
    public void saveData(List<Player> players) { // test si avec serializable sur Player ça marche
/*        try {
            FileOutputStream file = new FileOutputStream(filePath); // ouvrir un tube en écriture
            BufferedOutputStream buff = new BufferedOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(buff);
            for (Player player : players) {
                oos.writeObject(player.getPseudo());
                oos.writeObject(player.getScoreMax());
            }
            oos.close();
            buff.close(); // ou bien fermer le file tout court
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (Player player : players) {
                oos.writeUTF(player.getPseudo());
                oos.writeInt(player.getScoreMax());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
