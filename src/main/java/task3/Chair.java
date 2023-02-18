package task3;

public class Chair {
    Color color;
    int comfortability;
    boolean sit;

    public Chair(Color color, int comfortability, boolean sit) {
        this.color = color;
        this.comfortability = comfortability;
        this.sit = sit;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getComfortability() {
        return comfortability;
    }

    public void setComfortability(int comfortability) {
        this.comfortability = comfortability;
    }

    public boolean isSit() {
        return sit;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }
}
