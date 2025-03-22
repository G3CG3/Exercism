import java.util.*;

class Alphametics {
    private final String userInput;
    private Map<Character, Integer> letterMap;
    private boolean[] usedDigits;

    public Alphametics(String userInput) {
        this.userInput = userInput;
    }

    public Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        Set<Character> uniqueLetters = new LinkedHashSet<>();
        String[] parts = userInput.replace("+", " ").replace("=", " ").trim().split("\\s+");

        for (String part : parts) {
            for (char c : part.toCharArray()) {
                uniqueLetters.add(c);
            }
        }

        if (uniqueLetters.size() > 10) throw new UnsolvablePuzzleException();

        List<Character> letterList = new ArrayList<>(uniqueLetters);
        letterMap = new HashMap<>();
        usedDigits = new boolean[10];

        if (!solveBacktrack(letterList, 0, parts)) {
            throw new UnsolvablePuzzleException();
        }

        return letterMap;
    }

    private boolean solveBacktrack(List<Character> letters, int index, String[] words) {
        if (index == letters.size()) {
            return isValid(words);
        }

        char letter = letters.get(index);
        for (int digit = 0; digit < 10; digit++) {
            if (!usedDigits[digit]) {
                letterMap.put(letter, digit);
                usedDigits[digit] = true;

                if (solveBacktrack(letters, index + 1, words)) {
                    return true;
                }

                letterMap.remove(letter);
                usedDigits[digit] = false;
            }
        }
        return false;
    }

    private boolean isValid(String[] words) {
        for (String word : words) {
            if (letterMap.get(word.charAt(0)) == 0) return false;
        }

        int sum = 0;
        for (int i = 0; i < words.length - 1; i++) {
            sum += getNumericValue(words[i]);
        }

        return sum == getNumericValue(words[words.length - 1]);
    }

    private int getNumericValue(String word) {
        int value = 0;
        for (char c : word.toCharArray()) {
            value = value * 10 + letterMap.get(c);
        }
        return value;
    }
}