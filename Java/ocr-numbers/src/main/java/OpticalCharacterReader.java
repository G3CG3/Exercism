import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OpticalCharacterReader {
    private static final Map<String, Character> DIGIT_MAP = new HashMap<>();

    static {
        DIGIT_MAP.put(" _ | ||_|", '0');
        DIGIT_MAP.put("     |  |", '1');
        DIGIT_MAP.put(" _  _||_ ", '2');
        DIGIT_MAP.put(" _  _| _|", '3');
        DIGIT_MAP.put("   |_|  |", '4');
        DIGIT_MAP.put(" _ |_  _|", '5');
        DIGIT_MAP.put(" _ |_ |_|", '6');
        DIGIT_MAP.put(" _   |  |", '7');
        DIGIT_MAP.put(" _ |_||_|", '8');
        DIGIT_MAP.put(" _ |_| _|", '9');
    }

    String parse(List<String> input) {
        if (input == null || input.isEmpty() || input.size() % 4 != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }

        int width = input.getFirst().length();
        if (width % 3 != 0 || input.stream().anyMatch(line -> line.length() != width)) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }

        StringBuilder result = new StringBuilder();
        int numRows = input.size() / 4;
        int numCols = width / 3;

        for (int row = 0; row < numRows; row++) {
            if (row > 0) result.append(",");

            for (int col = 0; col < numCols; col++) {
                String digitStr = extractDigit(input, row * 4, col * 3);
                result.append(DIGIT_MAP.getOrDefault(digitStr, '?'));
            }
        }

        return result.toString();
    }

    private static String extractDigit(List<String> input, int row, int col) {
        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            digit.append(input.get(row + i), col, col + 3);
        }
        return digit.toString();
    }

}