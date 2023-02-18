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
    public boolean scream(Man other) {
        if (other.moodLevel >= 5) {
            other.moodLevel = other.moodLevel - 5;
            this.moodLevel += 5;
            return true;
        } else {
            return false;
        }

    }

}
