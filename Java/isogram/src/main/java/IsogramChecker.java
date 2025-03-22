import java.util.HashSet;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        phrase = phrase.toLowerCase();
        HashSet<Character> seen = new HashSet<>();

        for (char c : phrase.toCharArray()) {
            if (Character.isLetter(c)) {
                if (seen.contains(c)) {
                    return false;
                }
            }
            seen.add(c);
        }
        return true;
    }

}
