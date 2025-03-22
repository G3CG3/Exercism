import java.util.HashSet;
import java.util.Set;

class SumOfMultiples {
    private final int level;
    private final int[] base_values;

    SumOfMultiples(int number, int[] set) {
        level = number;
        base_values = set;
    }

    int getSum() {
        Set<Integer> uniqueMultiples = new HashSet<>();
        for (int base : base_values) {
            if (base == 0) {
                continue;
            }

            for (int multiple = base; multiple < level; multiple += base) {
                uniqueMultiples.add(multiple);
            }
        }
        int totalPoints = 0;
        for (int value : uniqueMultiples) {
            totalPoints += value;
        }
        return totalPoints;
    }

}
