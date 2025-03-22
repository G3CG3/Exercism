import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

class Meetup {
    private final int MONTH;
    private final int YEAR;

    Meetup(int monthOfYear, int year) {
        this.MONTH = monthOfYear;
        this.YEAR = year;
    }

    LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        YearMonth yearMonth = YearMonth.of(YEAR, MONTH);

        return switch (schedule) {
            case FIRST -> findNthWeekday(yearMonth, dayOfWeek, 1);
            case SECOND -> findNthWeekday(yearMonth, dayOfWeek, 2);
            case THIRD -> findNthWeekday(yearMonth, dayOfWeek, 3);
            case FOURTH -> findNthWeekday(yearMonth, dayOfWeek, 4);
            case LAST -> findLastWeekday(yearMonth, dayOfWeek);
            case TEENTH -> findTeenthWeekday(yearMonth, dayOfWeek);
        };
    }

    private LocalDate findNthWeekday(YearMonth yearMonth, DayOfWeek dayOfWeek, int n) {
        LocalDate date = yearMonth.atDay(1);
        int count = 0;

        while (date.getMonthValue() == yearMonth.getMonthValue()) {
            if (date.getDayOfWeek() == dayOfWeek) {
                count++;
                if (count == n) {
                    return date;
                }
            }
            date = date.plusDays(1);
        }
        throw new IllegalArgumentException("Invalid date computation.");
    }

    private LocalDate findLastWeekday(YearMonth yearMonth, DayOfWeek dayOfWeek) {
        LocalDate date = yearMonth.atEndOfMonth();

        while (date.getDayOfWeek() != dayOfWeek) {
            date = date.minusDays(1);
        }
        return date;
    }

    private LocalDate findTeenthWeekday(YearMonth yearMonth, DayOfWeek dayOfWeek) {
        LocalDate date = yearMonth.atDay(13); // Start from 13th
        for (int i = 0; i < 7; i++) {
            if (date.getDayOfWeek() == dayOfWeek) {
                return date;
            }
            date = date.plusDays(1);
        }
        throw new IllegalArgumentException("Invalid teenth date computation.");
    }
}