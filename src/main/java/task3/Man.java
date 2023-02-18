package task3;

import java.util.Optional;

public abstract class Man {
    protected int moodLevel;
    protected Mood moodType;
    protected Color bodyColor;
    protected Optional<Chair> chair;

    public Man(int moodLevel, Mood moodType, Color bodyColor) {
        this.moodLevel = moodLevel;
        this.moodType = moodType;
        this.bodyColor = bodyColor;
        this.chair = Optional.empty();
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }

    public Mood getMoodType() {
        return moodType;
    }

    public void setMoodType(Mood moodType) {
        this.moodType = moodType;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public Chair getChair() {
        return chair.orElse(null);
    }

    public void setChair(Chair chair) {
        this.chair = Optional.ofNullable(chair);
    }

    abstract public boolean scream(Man other);
    abstract public boolean sit(Chair chair);
    abstract public boolean up();
}
