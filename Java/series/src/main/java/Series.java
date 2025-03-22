import java.util.ArrayList;
import java.util.List;

class Series {
    private final String digits;

    Series(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("series cannot be empty");
        }
        this.digits = string;
    }

    List<String> slices(int num) {
        if (num > digits.length()) {
            throw new IllegalArgumentException("slice length cannot be greater than series length");
        }

        if (num <= 0) {
            throw new IllegalArgumentException("slice length cannot be negative or zero");
        }

        List<String> series = new ArrayList<>();
        for (int i = 0; i <= digits.length() - num; i++) {
            series.add(digits.substring(i, i + num));
        }
        return series;
    }
}
