import java.util.stream.Collectors;

class Atbash {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String REVERSED_ALPHABET = new StringBuilder(ALPHABET).reverse().toString();


    String encode(String input) {
        String processed = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        String encoded = processed.chars()
                .mapToObj(c -> Character.isLetter(c) ? REVERSED_ALPHABET.charAt(ALPHABET.indexOf((char) c)) : (char) c)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return formatOutput(encoded);
    }

    String decode(String input) {
        return input.replaceAll("[^a-z0-9]", "")
                .chars()
                .mapToObj(c -> Character.isLetter(c) ? ALPHABET.charAt(REVERSED_ALPHABET.indexOf((char) c)) : (char) c)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private String formatOutput(String input) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && i % 5 == 0) {
                formatted.append(" ");
            }
            formatted.append(input.charAt(i));
        }
        return formatted.toString();
    }
}
