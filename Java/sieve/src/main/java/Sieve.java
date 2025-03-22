import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sieve {
    private boolean[] isPrime;
    private List<Integer> primes;

    Sieve(int maxPrime) {
        if (maxPrime < 2) {
            primes = new ArrayList<>();
            return;
        }

        isPrime = new boolean[maxPrime + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= maxPrime; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxPrime; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        primes = new ArrayList<>();
        for (int i = 2; i <= maxPrime; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    List<Integer> getPrimes() {
        return primes;
    }
}
