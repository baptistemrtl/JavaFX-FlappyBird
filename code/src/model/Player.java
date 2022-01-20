package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * La classe Player
 */
public class Player {

    private final StringProperty pseudo = new SimpleStringProperty();

    /**
     * Get le pseudo
     *
     * @return le string du pseudo
     */
    public String getPseudo(){ return pseudo.get(); }

    /**
     * Set le pseudo
     *
     * @param psd le nouveau pseudo
     */
    public void setPseudo(String psd){ pseudo.set(psd); }

    /**
     * Le StringProperty du pseudo
     *
     * @return le string property
     */
    public StringProperty pseudoProperty(){ return pseudo; }

    private final IntegerProperty scoreMax = new SimpleIntegerProperty();

    /**
     * Get le score max
     *
     * @return le score max
     */
    public int getScoreMax(){
        return scoreMax.get();
        }

    /**
     * Set le score max
     *
     * @param smax le nouveau score max
     */
    public void setScoreMax(int smax){ scoreMax.set(smax);}

    /**
     * Le IntegerProperty du score max
     *
     * @return the integer property
     */
    public IntegerProperty scoreProperty(){ return scoreMax; }

    /**
     * Constructeur de Player
     *
     * @param pseudo   son pseudo
     * @param scoreMax son score max
     */
    public Player(String pseudo, int scoreMax) {
        setPseudo(pseudo);
        setScoreMax(scoreMax);
    }

    /**
     * Constructeur de Player
     *
     * @param pseudo son pseudo
     */
    public Player(String pseudo) {
        this(pseudo,0);
    }
}
