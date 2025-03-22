import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordCount {
    public Map<String, Integer> phrase(String input) {
        Pattern pattern = Pattern.compile("\\b\\w+(?:'\\w+)?\\b");
        Matcher matcher = pattern.matcher(input.toLowerCase());

        Map<String, Integer> wordCount = new HashMap<>();

        while (matcher.find()) {
            String word = matcher.group();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount;
    }
}
