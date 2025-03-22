class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        int originalNumber = numberToCheck;
        int sum = 0;
        int digits = String.valueOf(numberToCheck).length();

        while (numberToCheck > 0) {
            int digit = numberToCheck % 10;
            int power = 1;

            for (int i = 0; i < digits; i++) {
                power *= digit;
            }

            sum += power;
            numberToCheck /= 10;
        }
        return sum == originalNumber;
    }

}
