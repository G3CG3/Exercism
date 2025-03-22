public class AffineCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final int M = ALPHABET.length();

    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    public static void validateKey(int a) {
        if (gcd(a, M) != 1) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static String encode(String text, int a, int b) {
        validateKey(a);
        text = text.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int i = ALPHABET.indexOf(ch);
                char encryptedChar = ALPHABET.charAt((a * i + b) % M);
                encryptedText.append(encryptedChar);
            } else if (Character.isDigit(ch)) {
                encryptedText.append(ch);
            }
        }

        return encryptedText.toString().replaceAll("(.{5})", "$1 ").trim();
    }

    public static String decode(String text, int a, int b) {
        validateKey(a);
        int aInv = modInverse(a, M);
        if (aInv == -1) {
            throw new IllegalArgumentException("No modular inverse found for 'a' (" + a + ") mod " + M);
        }
        text = text.replace(" ", "");
        StringBuilder decryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int y = ALPHABET.indexOf(ch);
                char decryptedChar = ALPHABET.charAt((aInv * ((y - b + M) % M + M) % M) % M);
                decryptedText.append(decryptedChar);
            } else if (Character.isDigit(ch)) {
                decryptedText.append(ch);
            }
        }

        return decryptedText.toString();
    }
}