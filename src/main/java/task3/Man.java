package task3;

import java.util.Optional;

public abstract class Man {
    protected int moodLevel;
    protected Mood moodType;
    protected Color bodyColor;
    protected Optional<Chair> chair;
    protected Planet location;

    public Man(final int moodLevel, final Mood moodType, final Color bodyColor) {
        this.moodLevel = moodLevel;
        this.moodType = moodType;
        this.bodyColor = bodyColor;
        this.chair = Optional.empty();
    }

    public Chair getChair() {
        return chair.orElse(null);
    }

    abstract public boolean scream(Man other);
    abstract public boolean sit(Chair chair);
    abstract public boolean up();
}
