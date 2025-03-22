class RotationalCipher {
    private final int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder cipher = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = (c >= 'a') ? 'a' : 'A';
                cipher.append((char) ((c - base + shiftKey) % 26 + base));
            } else {
                cipher.append(c);
            }
        }

        return cipher.toString();
    }

}
