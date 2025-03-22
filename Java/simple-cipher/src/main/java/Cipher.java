import java.security.SecureRandom;
import java.util.Random;

public class Cipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final String key;

    public Cipher() {
        this.key = generateRandomKey();
    }

    public Cipher(String key) {
        if (!key.matches("[a-z]+")) {
            throw new IllegalArgumentException("Key must contain only lowercase letters.");
        }
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        return transform(plainText, true);
    }

    public String decode(String cipherText) {
        return transform(cipherText, false);
    }

    private String generateRandomKey() {
        Random random = new SecureRandom();
        StringBuilder randomKey = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            randomKey.append(ALPHABET.charAt(random.nextInt(26)));
        }
        return randomKey.toString();
    }

    private String transform(String input, boolean encode) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char plainChar = input.charAt(i);
            char keyChar = key.charAt(i % key.length());
            int shift = keyChar - 'a';
            int newCharIndex = encode
                    ? (plainChar - 'a' + shift) % 26
                    : (plainChar - 'a' - shift + 26) % 26;
            result.append(ALPHABET.charAt(newCharIndex));
        }
        return result.toString();
    }
}
