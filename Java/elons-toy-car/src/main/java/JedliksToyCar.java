public class JedliksToyCar {
    private int distanceDriven;
    private int batteryPercentage;

    public JedliksToyCar() {
        this.distanceDriven = 0;
        this.batteryPercentage = 100;
    }

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + this.distanceDriven + " meters";
    }

    public String batteryDisplay() {
        if (this.batteryPercentage == 0) {
            return "Battery empty";
        }
        return "Battery at " + this.batteryPercentage + "%";
    }

    public void drive() {
        if (this.batteryPercentage > 0) {
            distanceDriven += 20;
            batteryPercentage -= 1;
        }
    }
}
