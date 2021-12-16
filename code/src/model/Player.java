package model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Player {
    private final SimpleStringProperty pseudo = new SimpleStringProperty();
    private String icon;
    private final SimpleIntegerProperty scoreMax = new SimpleIntegerProperty();

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
