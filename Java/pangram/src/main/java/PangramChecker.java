import java.util.HashSet;

public class PangramChecker {

    public boolean isPangram(String input) {
        if (input.isEmpty()) {
            return false;
        }

        input = input.toLowerCase();
        HashSet<Character> letters = new HashSet<>();

        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters.add(c);
            }
        }

        return letters.size() == 26;
    }

}
