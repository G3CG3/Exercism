class NaturalNumber {
    private final int number;

    NaturalNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = number;
    }

    private int aliquotSum() {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    Classification getClassification() {
        int sumOfDivisors = aliquotSum();
        if (sumOfDivisors == number) {
            return Classification.PERFECT;
        } else if (sumOfDivisors > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }
}
