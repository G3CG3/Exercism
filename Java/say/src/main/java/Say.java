public class Say {
    private static final String[] UNITS = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] TEENS = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final String[] THOUSANDS = {"", "thousand", "million", "billion", "trillion"};


    public String say(long number) {
        if (number < 0 || number > 999_999_999_999L) {
            throw new IllegalArgumentException("Number out of range");
        }
        if (number == 0) {
            return "zero";
        }

        StringBuilder result = new StringBuilder();
        int chunkCount = 0;

        while (number > 0) {
            int chunk = (int) (number % 1000);
            if (chunk > 0) {
                String words = convertHundred(chunk) + (THOUSANDS[chunkCount].isEmpty() ? "" : " " + THOUSANDS[chunkCount]);
                result.insert(0, words + " ");
            }
            number /= 1000;
            chunkCount++;
        }

        return result.toString().trim();
    }

    private static String convertHundred(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 10) {
            return UNITS[num];
        }
        if (num < 20) {
            return TEENS[num - 10];
        }
        if (num < 100) {
            return TENS[num / 10] + (num % 10 != 0 ? "-" + UNITS[num % 10] : "");
        }
        return UNITS[num / 100] + " hundred" + (num % 100 != 0 ? " " + convertHundred(num % 100) : "");
    }
}
