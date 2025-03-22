import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    private final LocalDateTime gigasecondDateTime;

    public Gigasecond(LocalDate moment) {
        this(moment.atStartOfDay());
    }

    public Gigasecond(LocalDateTime moment) {
        this.gigasecondDateTime = moment.plusSeconds(1_000_000_000);
    }

    public LocalDateTime getDateTime() {
        return gigasecondDateTime;
    }
}
