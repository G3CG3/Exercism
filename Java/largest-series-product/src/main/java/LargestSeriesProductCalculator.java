class LargestSeriesProductCalculator {
    private final String inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {
        if (!inputNumber.matches("\\d*")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits > inputNumber.length() ) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (inputNumber.isEmpty()) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }

        long maxProduct = 0;
        long product = 1;
        int zeroCount = 0;

        for (int i = 0; i < inputNumber.length(); i++) {
            int digit = Character.getNumericValue(inputNumber.charAt(i));

            if (digit == 0) {
                zeroCount ++;
            } else {
                product *= digit;
            }

            if (i >= numberOfDigits) {
                int removedDigit = Character.getNumericValue(inputNumber.charAt(i - numberOfDigits));

                if (removedDigit == 0) {
                    zeroCount--;
                } else {
                    product /= removedDigit;
                }
            }

            if (i >= numberOfDigits - 1 && zeroCount == 0) {
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }
}
