package task3;

import lombok.Getter;

public class Planet {
    @Getter
    private final Color color;
    @Getter
    private int popularity;
    private boolean destroyed;

    public Planet(final Color color, final int popularity) {
        this.color = color;
        this.popularity = popularity;
        this.destroyed = false;
    }

    public void destroy() {
        this.destroyed = true;
        this.popularity = 0;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void relocatePopularity(final int relocatedCount) {
        this.popularity += relocatedCount;
    }
}
