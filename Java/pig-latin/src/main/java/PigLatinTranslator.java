import java.util.Arrays;
import java.util.stream.Collectors;

class PigLatinTranslator {
    public static String translate(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .map(PigLatinTranslator::translateWord)
                .collect(Collectors.joining(" "));
    }

    private static String translateWord(String word) {
        word = word.toLowerCase();

        if (word.matches("^(xr|yt|[aeiou]).*")) {
            return word + "ay";
        }

        if (word.matches("^[^aeiou]*qu.*")) {
            return word.replaceFirst("^([^aeiou]*qu)(.*)", "$2$1ay");
        }

        if (word.matches("^([^aeiouy]+)(y.*)")) {
            return word.replaceFirst("^([^aeiouy]+)(y.*)", "$2$1ay"); // Rule 4
        }

        return word.replaceFirst("^([^aeiou]+)(.*)", "$2$1ay");
    }

}