class RomanNumerals {
    private final int number;

    RomanNumerals(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 - 3999");
        }
        this.number = number;
    }

    String getRomanNumeral() {
        StringBuilder roman = new StringBuilder();

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int num = number;

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                roman.append(letters[i]);
                num -= values[i];
            }
        }

        return roman.toString();
    }
}
