import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Anagram {
    private final String target;
    private final String sortedTarget;

    public Anagram(String word) {
        this.target = word;
        this.sortedTarget = sortString(word.toLowerCase());
    }

    public List<String> match(List<String> candidates) {
        return candidates.stream()
                .filter(word -> !word.equalsIgnoreCase(target))
                .filter(word -> sortString(word.toLowerCase()).equals(sortedTarget))
                .collect(Collectors.toList());
    }

    private static String sortString(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}