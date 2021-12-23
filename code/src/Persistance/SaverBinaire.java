package Persistance;

import model.Player;

import java.io.*;
import java.util.List;

public class SaverBinaire implements Save{

    private String filePath;

    public SaverBinaire(String filePath){
        this.filePath = filePath;
    }

    public SaverBinaire(){
        this("");
    }

    @Override
    public void saveData(List<Player> players) {
        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
