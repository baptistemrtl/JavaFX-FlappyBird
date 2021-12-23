package model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Player {

    private final StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo(){ return pseudo.get(); }
        public void setPseudo(String psd){ pseudo.set(psd); }

    private IntegerProperty scoreMax = new SimpleIntegerProperty();
        public int getScoreMax(){ return scoreMax.get(); }
        public void setScoreMax(int smax){ scoreMax.set(smax);}

    public Player(String pseudo, int scoreMax) {
        setPseudo(pseudo);
        setScoreMax(scoreMax);
    }

    public Player(String pseudo){

        this(pseudo,0);
    }


}
