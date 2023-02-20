package task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chair {
    private final Color color;
    private final int comfortability;
    boolean sit;

    public Chair(final Color color, final int comfortability, final boolean sit) {
        this.color = color;
        this.comfortability = comfortability;
        this.sit = sit;
    }

    public boolean isSit() {
        return sit;
    }

    public void setSit(final boolean sit) {
        this.sit = sit;
    }
}
