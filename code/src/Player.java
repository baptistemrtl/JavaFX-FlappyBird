public class Player {
    private String pseudo;
    private String icon;
    private int scoreMax;

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
}
