import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> map = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entry : old.entrySet()) {
            int points = entry.getKey();
            for (String letter : entry.getValue()) {
                map.put(letter.toLowerCase(), points);
            }
        }
        return map;
    }
}
