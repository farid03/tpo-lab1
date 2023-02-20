package task3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chair {
    boolean sit;

    public Chair(final boolean sit) {
        this.sit = sit;
    }

    public boolean isSit() {
        return sit;
    }

    public void setSit(final boolean sit) {
        this.sit = sit;
    }
}
