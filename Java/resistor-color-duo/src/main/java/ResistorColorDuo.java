import java.util.*;

class ResistorColorDuo {
    private static final Map<String, Integer> colorMap = new HashMap<>();

    static {
        colorMap.put("black", 0);
        colorMap.put("brown", 1);
        colorMap.put("red", 2);
        colorMap.put("orange", 3);
        colorMap.put("yellow", 4);
        colorMap.put("green", 5);
        colorMap.put("blue", 6);
        colorMap.put("violet", 7);
        colorMap.put("grey", 8);
        colorMap.put("white", 9);
    }

    int value(String[] colors) {
        if (colors.length < 2) {
            throw new IllegalArgumentException("At least two colors are required.");
        }
        Integer firstDigit = colorMap.get(colors[0].toLowerCase());
        Integer secondDigit = colorMap.get(colors[1].toLowerCase());

        if (firstDigit == null || secondDigit == null) {
            throw new IllegalArgumentException("Invalid color provided.");
        }
        return firstDigit * 10 + secondDigit;
    }
}
