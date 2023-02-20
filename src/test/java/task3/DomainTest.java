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
        planet = new Planet(Color.GREEN, 0);
        captain = new Captain(10);
        chair = new Chair(false);
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
    @DisplayName("Check Captain's first comeTo method")
    void comeToTest() {
        captain.moveTo(planet);
        assertEquals(planet.getPopularity(), 1);
        assertEquals(captain.getLocation(), planet);
    }

    @Test
    @DisplayName("Check relocate from other planet")
    void relocateFromOtherPlanetTest() {
        captain.moveTo(planet);
        assertEquals(planet.getPopularity(), 1);
        assertEquals(captain.getLocation(), planet);

        final Planet otherPlanet = new Planet(Color.BLACK, 0);
        captain.moveTo(otherPlanet);
        assertEquals(planet.getPopularity(), 0);
        assertEquals(otherPlanet.getPopularity(), 1);
        assertEquals(captain.getLocation(), otherPlanet);
    }

    @Test
    @DisplayName("Basic emulation test")
    void basicEmulationTest() {
        final Planet otherPlanet = new Planet(Color.YELLOW, 0);
        final Captain otherMan = new Captain(10);
        final Emulation emulation = new Emulation(captain, otherMan, planet, otherPlanet, chair);

        emulation.start();
        assertAll(
                () -> assertEquals(planet, captain.getLocation()),
                () -> assertTrue(otherPlanet.isDestroyed()),
                () -> assertEquals(planet, otherMan.getLocation()),
                () -> assertEquals(15, captain.getMoodLevel()),
                () -> assertEquals(5, otherMan.getMoodLevel()),
                () -> assertEquals(captain.getChair(), chair),
                () -> assertTrue(chair.isSit())
        );
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
