import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Knapsack {
    private final Map<String, Integer> map = new HashMap<>();

    int maximumValue(int maximumWeight, List<Item> items) {
        return knapsack(items, maximumWeight, 0);
    }

    private int knapsack(List<Item> items, int remainingWeight, int index) {
        if (index >= items.size() || remainingWeight <= 0) {
            return 0;
        }

        String key = index + "," + remainingWeight;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        Item currentItem = items.get(index);
        int valueWithoutCurrent = knapsack(items, remainingWeight, index + 1);
        int valueWithCurrent = 0;

        if (currentItem.weight <= remainingWeight) {
            valueWithCurrent = currentItem.value + knapsack(items, remainingWeight - currentItem.weight, index + 1);
        }

        int result = Math.max(valueWithoutCurrent, valueWithCurrent);
        map.put(key, result);
        return result;
    }
}