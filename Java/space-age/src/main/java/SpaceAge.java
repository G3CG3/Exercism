class SpaceAge {
    private static final double EARTH_YEARS_SECONDS = 31_557_600;
    private static final double MERCURY_YEAR = 0.2408467;
    private static final double VENUS_YEAR = 0.61519726;
    private static final double EARTH_YEAR = 1.0;
    private static final double MARS_YEAR = 1.8808158;
    private static final double JUPITER_YEAR = 11.862615;
    private static final double SATURN_YEAR = 29.447498;
    private static final double URANUS_YEAR = 84.016846;
    private static final double NEPTUNE_YEAR = 164.79132;
    private final double seconds;

    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return seconds / EARTH_YEARS_SECONDS;
    }

    double onMercury() {
        return  seconds / (EARTH_YEARS_SECONDS * MERCURY_YEAR);
    }

    double onVenus() {
        return  seconds / (EARTH_YEARS_SECONDS * VENUS_YEAR);
    }

    double onMars() {
        return  seconds / (EARTH_YEARS_SECONDS * MARS_YEAR);
    }

    double onJupiter() {
        return  seconds / (EARTH_YEARS_SECONDS * JUPITER_YEAR);
    }

    double onSaturn() {
        return  seconds / (EARTH_YEARS_SECONDS * SATURN_YEAR);
    }

    double onUranus() {
        return seconds / (EARTH_YEARS_SECONDS * URANUS_YEAR);
    }

    double onNeptune() {
        return  seconds / (EARTH_YEARS_SECONDS * NEPTUNE_YEAR);
    }

}
