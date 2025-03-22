import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Yacht {
    private final int[] dice;
    private final YachtCategory category;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.dice = dice;
        this.category = yachtCategory;
    }

    int score() {
        Map<Integer, Integer> diceCounts = new HashMap<>();
        for (int roll : dice) {
            diceCounts.put(roll, diceCounts.getOrDefault(roll, 0) + 1);
        }

        return switch (category) {
            case ONES -> diceCounts.getOrDefault(1, 0);
            case TWOS -> diceCounts.getOrDefault(2, 0) * 2;
            case THREES -> diceCounts.getOrDefault(3, 0) * 3;
            case FOURS -> diceCounts.getOrDefault(4, 0) * 4;
            case FIVES -> diceCounts.getOrDefault(5, 0) * 5;
            case SIXES -> diceCounts.getOrDefault(6, 0) * 6;
            case FULL_HOUSE -> {
                if (diceCounts.containsValue(3) && diceCounts.containsValue(2)) {
                    yield Arrays.stream(dice).sum();
                }
                yield 0;
            }
            case FOUR_OF_A_KIND -> diceCounts.entrySet().stream().filter(entry -> entry.getValue() >= 4)
                    .map(entry -> entry.getKey() * 4)
                    .findAny()
                    .orElse(0);
            case LITTLE_STRAIGHT -> {
                Arrays.sort(dice);
                yield Arrays.equals(dice, new int[]{1, 2, 3, 4, 5}) ? 30 : 0;
            }
            case BIG_STRAIGHT -> {
                Arrays.sort(dice);
                yield Arrays.equals(dice, new int[]{2, 3, 4, 5, 6}) ? 30 : 0;
            }
            case CHOICE -> Arrays.stream(dice).sum();
            case YACHT -> diceCounts.size() == 1 ? 50 : 0;
            default -> 0;
        };
    }

}
