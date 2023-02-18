package task3;

public class Captain extends Man {

    public Captain(int moodLevel) {
        super(moodLevel, Mood.ANGRY, Color.GREEN);
    }

    boolean destroy(Planet planet) {
        if (planet.isDestroyed()) {
            return false;
        } else {
            planet.setDestroyed(true);
        }
        this.setMoodType(Mood.ANNOYED);
        return true;
    }

    @Override
    public void scream(Man other) {
        other.moodLevel = Math.max(other.moodLevel - 5, 0);
        this.moodLevel += 5;
    }

}
