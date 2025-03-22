import java.util.HashMap;
import java.util.Map;

class Scrabble {
    private static final Map<Character, Integer> LETTER_VALUES = new HashMap<>();
    private int score = 0;

    static {
        String[] letters = {"AEIOULNRST", "DG", "BCMP", "FHVWY", "K", "JX", "QZ"};
        int[] values = {1, 2, 3, 4, 5, 8, 10};

        for (int i = 0; i < letters.length; i++) {
            for (char c : letters[i].toCharArray()) {
                LETTER_VALUES.put(c, values[i]);
            }
        }
    }

    Scrabble(String word) {
        for (char c : word.toUpperCase().toCharArray()) {
            score += LETTER_VALUES.getOrDefault(c, 0);
        }
    }

    int getScore() {
        return score;
    }

}
