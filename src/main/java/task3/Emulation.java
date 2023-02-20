package task3;

public class Emulation {
    private final Captain captain;
    private final Captain otherMan;
    private final Planet ourPlanet;
    private final Planet otherPlanet;
    private final Chair chair;

    public Emulation(final Captain captain,
                     final Captain otherMan,
                     final Planet ourPlanet,
                     final Planet otherPlanet,
                     final Chair chair) {
        this.captain = captain;
        this.otherMan = otherMan;
        this.ourPlanet = ourPlanet;
        this.otherPlanet = otherPlanet;
        this.chair = chair;
    }

    public void start() {
        captain.moveTo(ourPlanet);
        captain.destroy(otherPlanet);
        otherMan.moveTo(ourPlanet);
        captain.scream(otherMan);
        captain.sit(chair);
    }
}
