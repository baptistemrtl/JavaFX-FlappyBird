package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    private final StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo(){ return pseudo.get(); }
        public void setPseudo(String psd){ pseudo.set(psd); }
    public StringProperty pseudoProperty(){ return pseudo; }

    private IntegerProperty scoreMax = new SimpleIntegerProperty();
        public int getScoreMax(){
            if (scoreMax == null){
                setScoreMax(0);
            }
            return scoreMax.get();
        }
        public void setScoreMax(int smax){ scoreMax.set(smax);}
    public IntegerProperty scoreProperty(){ return scoreMax; }

    public Player(String pseudo, int scoreMax) {
        setPseudo(pseudo);
        setScoreMax(scoreMax);
    }

    public Player(String pseudo) {
        this(pseudo,0);
    }
}
