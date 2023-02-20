package task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planet {
    private final Color color;
    private int popularity;
    private final int size;
    boolean destroyed;

    public Planet(final Color color, final int popularity, final int size) {
        this.color = color;
        this.popularity = popularity;
        this.size = size;
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
