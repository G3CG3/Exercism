import java.util.Map;
import java.util.stream.Collectors;

class ParallelLetterFrequency {
    private final String[] TEXTS;

    ParallelLetterFrequency(String[] texts) {
        this.TEXTS = texts;
    }

    Map<Character, Integer> countLetters() {
        return String.join(" ", TEXTS)
                .chars()
                .parallel()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .collect(Collectors.groupingBy(Character::toLowerCase, Collectors.summingInt(c -> 1)));
    }

}