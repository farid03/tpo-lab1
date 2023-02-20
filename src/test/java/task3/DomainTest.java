package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {
    private static Planet planet;
    private static Captain captain;
    private static Chair chair;

    @BeforeEach
    void  setUp() {
        planet = new Planet(Color.GREEN, 1, 1);
        captain = new Captain(10);
        chair = new Chair(Color.GREEN, 2, false);
    }

    @Test
    @DisplayName("Check default planet destroy")
    void planetDestroyTest() {
        assertTrue(captain.destroy(planet));
        assertTrue(planet.isDestroyed());
    }

    @Test
    @DisplayName("Check repeated planet destroy")
    void planetRepeatedDestroyTest() {
        assertTrue(captain.destroy(planet));
        assertTrue(planet.isDestroyed());

        assertFalse(captain.destroy(planet));
        assertTrue(planet.isDestroyed());
    }

    @Test
    @DisplayName("Check man sit method")
    void manSitTest() {
        assertTrue(captain.sit(chair));
        assertTrue(chair.isSit());
        assertEquals(captain.getChair(), chair);
    }

    @Test
    @DisplayName("Check repeated sit on chair")
    void chairRepeatedSitTest() {
        assertTrue(captain.sit(chair));
        assertTrue(chair.isSit());
        assertEquals(captain.getChair(), chair);

        assertFalse(captain.sit(chair));
        assertEquals(captain.getChair(), chair);
        assertTrue(chair.isSit());
    }

    @Test
    @DisplayName("Check man up method")
    void manUpTest() {
        captain.sit(chair);
        assertTrue(captain.up());
        assertFalse(chair.isSit());
        assertNull(captain.getChair());
    }

    @Test
    @DisplayName("Check repeated man up method")
    void manRepeatedUpTest() {
        captain.sit(chair);
        assertTrue(captain.up());
        assertFalse(chair.isSit());
        assertNull(captain.getChair());

        assertFalse(captain.up());
        assertNull(captain.getChair());
        assertFalse(chair.isSit());
    }

    @Test
    @DisplayName("Check man scream method with normal mood")
    void manScreamTest() {
        final var otherCaptain = new Captain(10);
        final var otherCaptainStartMoodLevel = otherCaptain.getMoodLevel();
        final var currentCaptainStartMoodLevel = captain.getMoodLevel();

        assertTrue(captain.scream(otherCaptain));
        assertTrue(otherCaptain.getMoodLevel() < otherCaptainStartMoodLevel);
        assertTrue(captain.getMoodLevel() > currentCaptainStartMoodLevel);
    }

    @Test
    @DisplayName("Check man scream method with low mood")
    void manScreamWithLowMoodTest() {
        final var otherCaptain = new Captain(4);
        final var otherCaptainStartMoodLevel = otherCaptain.getMoodLevel();
        final var currentCaptainStartMoodLevel = captain.getMoodLevel();

        assertFalse(captain.scream(otherCaptain));
        assertEquals(otherCaptain.getMoodLevel(), otherCaptainStartMoodLevel);
        assertEquals(captain.getMoodLevel(), currentCaptainStartMoodLevel);
    }
}
