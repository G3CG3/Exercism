class NeedForSpeed {
    private final int speed;
    private final int batteryDrain;
    private int distanceDriven;
    private int batteryPercentage;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distanceDriven = 0;
        this.batteryPercentage = 100;
    }

    public boolean batteryDrained() {
        return this.batteryDrain > this.batteryPercentage;
    }

    public int distanceDriven() {
        return this.distanceDriven;
    }

    public void drive() {
        if (!batteryDrained()) {
            this.distanceDriven += this.speed;
            this.batteryPercentage -= this.batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }

    public int getBatteryDrain() {
        return batteryDrain;
    }

    public int getSpeed() {
        return speed;
    }
}

class RaceTrack {
    private final int distance;

    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        int maxDistance = (car.getBatteryDrain() > 0) ? (100 / car.getBatteryDrain()) * car.getSpeed() : 0;
        return maxDistance >= this.distance;
    }
}
