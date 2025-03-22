import java.util.Objects;

class Clock {
    private int hours;
    private int minutes;

    Clock(int hours, int minutes) {
        setTime(hours, minutes);
    }

    private void setTime(int hours, int minutes) {
        int totalMinutes = (hours * 60 + minutes) % (24 * 60);
        if (totalMinutes < 0) {
            totalMinutes += 24 * 60;
        }
        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    void add(int minutes) {
        setTime(this.hours, this.minutes + minutes);
    }

    void subtract(int minutes) {
        setTime(this.hours, this.minutes - minutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Clock clock = (Clock) obj;
        return hours == clock.hours && minutes == clock.minutes;
    }

    public int hashCode() {
        return Objects.hash(hours, minutes);
    }
}