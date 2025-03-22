import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> factors = new ArrayList<>();

        while (number % 2 == 0) {
            factors.add((long) 2);
            number /= 2;
        }

        for (int i = 3; (long) i * i <= number; i += 2) {
            while (number % i == 0) {
                factors.add((long) i);
                number /= i;
            }
        }

        if (number > 2) {
            factors.add(number);
        }

        return factors;
    }

}