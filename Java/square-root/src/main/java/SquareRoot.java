public class SquareRoot {
    public int squareRoot(int radicand) {
        if (radicand < 1) {
            throw new IllegalArgumentException("Input must be a positive whole number.");
        }

        int left = 1, right = radicand;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int square = mid * mid;

            if (square == radicand) {
                return mid;
            } else if (square < radicand) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        throw new IllegalArgumentException("No exact square root found.");
    }
}
