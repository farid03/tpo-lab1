package task3;

import lombok.Data;

@Data
public class Chair {
    Color color;
    int comfortability;
    boolean sit;

    public Chair(Color color, int comfortability, boolean sit) {
        this.color = color;
        this.comfortability = comfortability;
        this.sit = sit;
    }

    public boolean isSit() {
        return sit;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }
}
