import java.util.ArrayList;

class CryptoSquare {
    private final String normalizedText;
    private int r;
    private final int c;

    CryptoSquare(String plaintext) {
        // Normalize the input: remove spaces and punctuation, convert to lowercase
        this.normalizedText = plaintext.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int length = this.normalizedText.length();

        // Determine the number of rows and columns
        this.c = (int) Math.ceil(Math.sqrt(length));
        this.r = (int) Math.floor(Math.sqrt(length));
        if (r * c < length) {
            this.r = this.c;
        }
    }

    String getCiphertext() {
        // Construct the rectangle
        char[][] grid = new char[r][c];
        for (int i = 0, index = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (index < normalizedText.length()) {
                    grid[i][j] = normalizedText.charAt(index++);
                } else {
                    grid[i][j] = ' '; // Padding for perfect rectangle
                }
            }
        }

        // Read the columns to form the encoded message
        ArrayList<String> encodedParts = new ArrayList<>();
        for (int j = 0; j < c; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                sb.append(grid[i][j]);
            }
            encodedParts.add(sb.toString());
        }

        // Join with spaces to get the final encoded message
        return String.join(" ", encodedParts);
    }

}
