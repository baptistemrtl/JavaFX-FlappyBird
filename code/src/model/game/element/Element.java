package model.game.element;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Element {
    private Position pos;
    private int width;
    private int height;
    private final StringProperty image = new SimpleStringProperty();
        public String getImage() {
            return image.get();
        }
        public void setImage(String img) {
            image.set(img);
        }


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

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Element other = (Element) obj;
        return other.getPos() == this.getPos();
    }
}
