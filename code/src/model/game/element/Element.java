package model.game.element;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Position;

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
}