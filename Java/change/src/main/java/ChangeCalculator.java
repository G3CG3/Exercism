import java.util.*;

class ChangeCalculator {
    private final List<Integer> coins;

    ChangeCalculator(List<Integer> currencyCoins) {
        this.coins = new ArrayList<>(currencyCoins);
    }

    List<Integer> computeMostEfficientChange(int grandTotal) {
        if(grandTotal < 0) throw new IllegalArgumentException("Negative totals are not allowed.");

        int[] dp = new int[grandTotal + 1];

        int[] coinUsed = new int[grandTotal + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= grandTotal; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    coinUsed[i] = coin; // Track the coin used to make the amount i
                }
            }
        }

        // If dp[grandTotal] is still Integer.MAX_VALUE, it means no solution was found
        if (dp[grandTotal] == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(String.format("The total %d cannot be represented in the given currency.", grandTotal)); // No valid solution
        }

        // Reconstruct the coin combination that makes up the grandTotal
        List<Integer> result = new ArrayList<>();
        int amount = grandTotal;
        while (amount > 0) {
            int coin = coinUsed[amount];
            result.add(coin);
            amount -= coin;
        }

        Collections.reverse(result);
        return result;
    }
}
