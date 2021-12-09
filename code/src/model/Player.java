package model;
import javafx.scene.image.ImageView;

public class Player {
    private String pseudo;
    private String icon;
    private int scoreMax;
    private Position position;
    private int PLAYER_WIDTH = 123;
    private int PLAYER_HEIGHT = 123;
    private ImageView image; /* s'instancierai avec l'url de icon donc jsp laquelle garder*/

    public Player(String pseudo, String icon, int scoreMax) {
        this.pseudo = pseudo;
        this.icon = icon;
        this.scoreMax = scoreMax;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public int getPLAYER_WIDTH(){ return PLAYER_WIDTH; }

    public void setPLAYER_WIDTH(int width){ PLAYER_WIDTH = width; }

    public int getPLAYER_HEIGHT(){ return PLAYER_HEIGHT; }

    public void setPLAYER_HEIGHT(int height){ PLAYER_HEIGHT = height; }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p){ position = p; }

}
