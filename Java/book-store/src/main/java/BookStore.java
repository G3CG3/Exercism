import java.util.*;

class BookStore {
    private static final double BOOK_PRICE = 8.0;
    private static final double[] DISCOUNTS = {0.0, 0.05, 0.10, 0.20, 0.25};

    double calculateBasketCost(List<Integer> books) {
        Map<Integer, Integer> bookCounts= new HashMap<>();
        for (int book : books) {
            bookCounts.put(book, bookCounts.getOrDefault(book, 0) + 1);
        }
        List<Integer> bookFrequencies = new ArrayList<>(bookCounts.values());
        bookFrequencies.sort(Collections.reverseOrder());

        return findOptimalCost(bookFrequencies);
    }

    private double findOptimalCost(List<Integer> bookFrequencies) {
        if (bookFrequencies.isEmpty() || bookFrequencies.stream().allMatch(count -> count == 0)) {
            return 0.0;
        }

        double minCost = Double.MAX_VALUE;

        for (int setSize = 5; setSize >= 1; setSize--) {
            if (bookFrequencies.size() < setSize) continue;

            List<Integer> newFrequencies = new ArrayList<>(bookFrequencies);
            for (int i = 0; i < setSize; i++) {
                newFrequencies.set(i, newFrequencies.get(i) - 1);
            }

            newFrequencies.removeIf(count -> count == 0);

            double cost = setSize * BOOK_PRICE * (1 - DISCOUNTS[setSize - 1]) + findOptimalCost(newFrequencies);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
}