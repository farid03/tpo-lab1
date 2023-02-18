package task3;

public abstract class Man {

    protected int moodLevel;
    protected Mood moodType;
    protected Color bodyColor;

    public Man(int moodLevel, Mood moodType, Color bodyColor) {
        this.moodLevel = moodLevel;
        this.moodType = moodType;
        this.bodyColor = bodyColor;
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

    abstract public boolean scream(Man other);


}
