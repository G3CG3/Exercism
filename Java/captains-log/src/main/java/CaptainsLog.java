import java.util.Random;

class CaptainsLog {

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    private final Random random;

    CaptainsLog(Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        int index = random.nextInt(PLANET_CLASSES.length);
        return PLANET_CLASSES[index];
    }

    String randomShipRegistryNumber() {
        int registryNumber = random.nextInt(9000)+ 1000;
        return "NCC-" + registryNumber;
    }

    double randomStardate() {
        double base = 41000f;
        double range = 1000f;
        return base + random.nextDouble() * range;
    }
}
