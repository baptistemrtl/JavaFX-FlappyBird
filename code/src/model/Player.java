package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Player {
    private StringProperty pseudo;
    private String icon;
    private IntegerProperty scoreMax;

    public Player(String pseudo, String icon, int scoreMax) {
        this.pseudo.set(pseudo);
        this.icon = icon;
        this.scoreMax.set(scoreMax);
    }

    public Player(String pseudo,String icon){
        this(pseudo,icon,0);
    }


    public String getPseudo() {
        return pseudo.get();
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScoreMax() {
        return scoreMax.get();
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax.set(scoreMax);
    }
}
