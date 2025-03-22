import java.util.ArrayList;
import java.util.List;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(new ArrayList<>(), 1, cageSize, cageSum, 0, exclude, result);
        return result;
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        return combinationsInCage(cageSum, cageSize, null);
    }

    private static void generateCombinations(List<Integer> current, int start, int cageSize, int cageSum,
                                             int currentSum, List<Integer> exclude, List<List<Integer>> result) {
        if (current.size() == cageSize) {
            if (currentSum == cageSum) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (exclude != null && exclude.contains(i)) {
                continue;
            }
            if (currentSum + i > cageSum) {
                break;
            }

            current.add(i);
            generateCombinations(current, i + 1, cageSize, cageSum, currentSum + i, exclude, result);
            current.removeLast();
        }
    }
}
