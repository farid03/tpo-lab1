package task3;

import lombok.Data;

@Data
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

    public boolean isDestroyed() {
        return destroyed;
    }
}
