import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FoodChain {
    private static final String[] ANIMALS = {
            "fly",
            "spider",
            "bird",
            "cat",
            "dog",
            "goat",
            "cow",
            "horse"
    };
    private static final String[] REACTIONS = {
            "I don't know why she swallowed the fly. Perhaps she'll die.",
            "It wriggled and jiggled and tickled inside her.",
            "How absurd to swallow a bird!",
            "Imagine that, to swallow a cat!",
            "What a hog, to swallow a dog!",
            "Just opened her throat and swallowed a goat!",
            "I don't know how she swallowed a cow!",
            "She's dead, of course!"
    };

    String verse(int verse) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("I know an old lady who swallowed a " + ANIMALS[--verse] + ".");
        if (verse > 0) {
            sj.add(REACTIONS[verse]);
        }
        if (verse == 7) {
            return sj.toString();
        }
        while (verse > 0) {
            sj.add("She swallowed the " + ANIMALS[verse] + " to catch the " + ANIMALS[--verse]
                    + (verse == 1 ? " that wriggled and jiggled and tickled inside her." : "."));
        }
        sj.add(REACTIONS[0]);
        return sj.toString();
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.rangeClosed(startVerse, endVerse)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n".repeat(2)));
    }
}