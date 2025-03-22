class BaseConverter {
    private final int originalBase;
    private final int[] originalDigits;

    public BaseConverter(int originalBase, int[] originalDigits) {
        if (originalBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }
        if (originalDigits.length == 0) {
            this.originalDigits = new int[]{0}; // Treat empty input as zero
        } else {
            for (int digit : originalDigits) {
                if (digit < 0) {
                    throw new IllegalArgumentException("Digits may not be negative.");
                }
                if (digit >= originalBase) {
                    throw new IllegalArgumentException("All digits must be strictly less than the base.");
                }
            }
            this.originalDigits = originalDigits;
        }
        this.originalBase = originalBase;
    }

    public int[] convertToBase(int newBase) {
        if (newBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }

        int value = 0;

        // Process input digits and compute decimal value, ignoring leading zeros
        int startIndex = 0;
        while (startIndex < originalDigits.length - 1 && originalDigits[startIndex] == 0) {
            startIndex++; // Skip leading zeros
        }

        for (int i = startIndex; i < originalDigits.length; i++) {
            value = value * originalBase + originalDigits[i];
        }

        // Edge case: If value is zero, return [0]
        if (value == 0) {
            return new int[]{0};
        }

        java.util.List<Integer> resultList = new java.util.ArrayList<>();
        while (value > 0) {
            resultList.add(value % newBase);
            value /= newBase;
        }

        // Convert result list to array in correct order
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(resultList.size() - 1 - i);
        }

        return result;
    }
}