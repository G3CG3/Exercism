import java.util.HashMap;
import java.util.Map;

class ResistorColorTrio {
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

    String label(String[] colors) {
        if (colors.length < 3) {
            throw new IllegalArgumentException("Exactly three colors are required.");
        }
        Integer firstDigit = colorMap.get(colors[0].toLowerCase());
        Integer secondDigit = colorMap.get(colors[1].toLowerCase());
        Integer multiplier = colorMap.get(colors[2].toLowerCase());

        if (firstDigit == null || secondDigit == null || multiplier == null) {
            throw new IllegalArgumentException("Invalid color provided.");
        }
        long resistanceValue = (firstDigit * 10L + secondDigit) * (long) Math.pow(10, multiplier);

        String unit = "ohms";
        if (resistanceValue >= 1_000_000_000L) {
            resistanceValue /= 1_000_000_000L;
            unit = "gigaohms";
        } else if (resistanceValue >= 1_000_000L) {
            resistanceValue /= 1_000_000L;
            unit = "megaohms";
        } else if (resistanceValue >= 1_000L) {
            resistanceValue /= 1_000L;
            unit = "kiloohms";
        }

        return resistanceValue + " " + unit;
    }
}
