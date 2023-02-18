import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task3.Captain;
import task3.Chair;
import task3.Color;
import task3.Planet;

import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {
    private static Planet planet;
    private static Captain captain;
    private static Chair chair;

    @BeforeEach
    public void init() {
        planet = new Planet(Color.GREEN, 1, 1);
        captain = new Captain(10);
        chair = new Chair(Color.GREEN, 2, false);
    }

    @Test
    @DisplayName("Check default planet destroy")
    public void planetDestroyTest() {
        assertTrue(captain.destroy(planet));
        assertTrue(planet.isDestroyed());
    }

    @Test
    @DisplayName("Check repeated planet destroy")
    public void planetRepeatedDestroyTest() {
        assertTrue(captain.destroy(planet));
        assertTrue(planet.isDestroyed());

        assertFalse(captain.destroy(planet));
        assertTrue(planet.isDestroyed());
    }

    @Test
    @DisplayName("Check man sit method")
    public void manSitTest() {
        assertTrue(captain.sit(chair));
        assertTrue(chair.isSit());
        assertEquals(captain.getChair(), chair);
    }

    @Test
    @DisplayName("Check repeated sit on chair")
    public void chairRepeatedSitTest() {
        assertTrue(captain.sit(chair));
        assertTrue(chair.isSit());
        assertEquals(captain.getChair(), chair);

        assertFalse(captain.sit(chair));
        assertEquals(captain.getChair(), chair);
        assertTrue(chair.isSit());
    }

    @Test
    @DisplayName("Check man up method")
    public void manUpTest() {
        captain.sit(chair);
        assertTrue(captain.up());
        assertFalse(chair.isSit());
        assertNull(captain.getChair());
    }

    @Test
    @DisplayName("Check repeated man up method")
    public void manRepeatedUpTest() {
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
    public void manScreamTest() {
        var otherCaptain = new Captain(10);
        var otherCaptainStartMoodLevel = otherCaptain.getMoodLevel();
        var currentCaptainStartMoodLevel = captain.getMoodLevel();

        assertTrue(captain.scream(otherCaptain));
        assertTrue(otherCaptain.getMoodLevel() < otherCaptainStartMoodLevel);
        assertTrue(captain.getMoodLevel() > currentCaptainStartMoodLevel);
    }

    @Test
    @DisplayName("Check man scream method with low mood")
    public void manScreamWithLowMoodTest() {
        var otherCaptain = new Captain(4);
        var otherCaptainStartMoodLevel = otherCaptain.getMoodLevel();
        var currentCaptainStartMoodLevel = captain.getMoodLevel();

        assertFalse(captain.scream(otherCaptain));
        assertEquals(otherCaptain.getMoodLevel(), otherCaptainStartMoodLevel);
        assertEquals(captain.getMoodLevel(), currentCaptainStartMoodLevel);
    }
}
