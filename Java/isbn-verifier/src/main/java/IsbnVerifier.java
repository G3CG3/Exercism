class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        stringToVerify = stringToVerify.replaceAll("-", "");

        if (stringToVerify.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char c = stringToVerify.charAt(i);

            int value;
            if (i == 9 && c == 'X') {
                value = 10;
            } else if (Character.isDigit(c)) {
                value = Character.getNumericValue(c);
            } else {
                return false;
            }
            sum += value * (10 - i);
        }
        return sum % 11 == 0;
    }

}
