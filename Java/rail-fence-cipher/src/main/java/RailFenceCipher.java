class RailFenceCipher {
    private int rows;

    RailFenceCipher(int rows) {
        this.rows = rows;
    }

    String getEncryptedData(String message) {
        message = message.replaceAll(" ", "").toUpperCase();
        char[][] rail = new char[rows][message.length()];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < message.length(); j++) {
                rail[i][j] = '.';
            }
        }

        int row = 0;
        boolean down = true;
        for (int col = 0; col < message.length(); col++) {
            rail[row][col] = message.charAt(col);

            if (down) {
                row++;
                if (row == rows) {
                    down = false;
                    row = rows - 2;
                }
            } else {
                row--;
                if (row == -1) {
                    down = true;
                    row = 1;
                }
            }
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (rail[i][j] != '.') {
                    encryptedMessage.append(rail[i][j]);
                }
            }
        }

        return encryptedMessage.toString();
    }

    String getDecryptedData(String message) {
        char[][] rail = new char[rows][message.length()];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < message.length(); j++) {
                rail[i][j] = '.';
            }
        }

        int row = 0;
        boolean down = true;
        for (int col = 0; col < message.length(); col++) {
            rail[row][col] = '*';  // Placeholder for message characters

            if (down) {
                row++;
                if (row == rows) {
                    down = false;
                    row = rows - 2;
                }
            } else {
                row--;
                if (row == -1) {
                    down = true;
                    row = 1;
                }
            }
        }

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (rail[i][j] == '*' && index < message.length()) {
                    rail[i][j] = message.charAt(index++);
                }
            }
        }

        // Read the decrypted message from the rail array
        StringBuilder decryptedMessage = new StringBuilder();
        row = 0;
        down = true;
        for (int col = 0; col < message.length(); col++) {
            decryptedMessage.append(rail[row][col]);

            if (down) {
                row++;
                if (row == rows) {
                    down = false;
                    row = rows - 2;
                }
            } else {
                row--;
                if (row == -1) {
                    down = true;
                    row = 1;
                }
            }
        }

        return decryptedMessage.toString();
    }

}