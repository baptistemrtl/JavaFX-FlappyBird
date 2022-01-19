package model.game.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe qui représente un Element de la vue
 */
public abstract class Element {
    private Position pos;
    private int width;
    private int height;

    //Propriété pour se bind sur le filepath de l'image de l'élément
    private final StringProperty image = new SimpleStringProperty();
        public String getImage() { return image.get(); }
        public void setImage(String img) { image.set(img); }
    public StringProperty imageProperty() { return image; }

    public Element(int width, int height, Position pos, String imageUrl) {
        setWidth(width);
        setHeight(height);
        setPos(pos);
        setImage(imageUrl);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Redéfinition de la fonction equals pour comparer des éléments en fonctions de leur position
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element element)) return false;

        return getWidth() == element.getWidth() && getHeight() == element.getHeight() &&
                getPos().equals(element.getPos()) && getImage().equals(element.getImage());
    }
}
