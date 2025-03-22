class LuhnValidator {

    public static boolean isValid(String candidate) {
        candidate = candidate.replaceAll("\\s", "");
        if (candidate.length() <= 1 || !candidate.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        boolean doubleDigit = false;

        for (int i = candidate.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(candidate.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0;
    }
}
