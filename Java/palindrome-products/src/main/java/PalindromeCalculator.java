import java.util.*;

class PalindromeCalculator {

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        SortedMap<Long, List<List<Integer>>> palindromeProducts = new TreeMap<>();

        for (int i = minFactor; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j++) { // Avoid duplicate pairs
                long product = (long) i * j;
                if (isPalindrome(product)) {
                    palindromeProducts.computeIfAbsent(product, k -> new ArrayList<>()).add(Arrays.asList(i, j));
                }
            }
        }

        return palindromeProducts;
    }

    private static boolean isPalindrome(long number) {
        String str = Long.toString(number);
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}