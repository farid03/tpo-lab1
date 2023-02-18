package task3;

public class Planet {

    Color color;
    int popularity;
    int size;
    boolean destroyed;

    public Planet(Color color, int popularity, int size) {
        this.color = color;
        this.popularity = popularity;
        this.size = size;
        this.destroyed = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
