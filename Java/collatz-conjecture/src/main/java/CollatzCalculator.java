class CollatzCalculator {
    private int count = 0;

    int computeStepCount(int start) {
        if (start <= 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        while (start > 1) {
            if (start % 2 == 0) {
                start /= 2;
            } else {
                start = (start * 3) + 1;
            }
            count ++;
        }
        return count;
    }

}
