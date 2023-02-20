package task3;

import java.util.Optional;

public class Captain extends Man {

    public Captain(final int moodLevel) {
        super(moodLevel, Mood.ANGRY, Color.GREEN);
    }

    public boolean destroy(final Planet planet) {
        if (planet.isDestroyed()) {
            return false;
        } else {
            planet.setDestroyed(true);
        }
        this.moodType = Mood.ANNOYED;
        return true;
    }

    @Override
    public boolean scream(final Man other) {
        if (other.moodLevel >= 5) {
            other.moodLevel = other.moodLevel - 5;
            this.moodLevel += 5;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sit(final Chair chair) {
        if (chair.isSit() || this.chair.isPresent()) {
            return false;
        } else {
            chair.setSit(true);
            this.chair = Optional.of(chair);
            return true;
        }

    }

    @Override
    public boolean up() {
        if (this.chair.isEmpty()) {
            return false;
        } else {
            this.chair.get().setSit(false);
            this.chair = Optional.empty();
            return true;
        }
    }

    public Mood getMood() {
        return this.moodType;
    }

    public int getMoodLevel() {
        return this.moodLevel;
    }
}
