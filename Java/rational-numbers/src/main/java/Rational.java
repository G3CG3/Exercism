import java.util.Objects;

class Rational {
    private final int numerator;
    private final int denominator;

    Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // Ensure standard form: denominator must be positive
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational add(Rational other) {
        return new Rational(numerator * other.denominator + other.numerator * denominator, denominator * other.denominator);
    }

    Rational subtract(Rational other) {
        return new Rational(numerator * other.denominator - other.numerator * denominator, denominator * other.denominator);
    }

    Rational multiply(Rational other) {
        return new Rational(numerator * other.numerator, denominator * other.denominator);
    }

    Rational divide(Rational other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Rational(numerator * other.denominator, denominator * other.numerator);
    }

    Rational abs() {
        return new Rational(Math.abs(numerator), Math.abs(denominator));
    }

    Rational pow(int power) {
        if (power < 0) {
            // Handle negative powers by inverting the base
            int m = Math.abs(power);
            return new Rational((int) Math.pow(denominator, m), (int) Math.pow(numerator, m));
        } else {
            // Handle non-negative powers
            return new Rational((int) Math.pow(numerator, power), (int) Math.pow(denominator, power));
        }
    }

    double exp(double exponent) {
        return Math.pow(exponent, (double) numerator / denominator);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rational other) {
            return this.getNumerator() == other.getNumerator()
                    && this.getDenominator() == other.getDenominator();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumerator(), this.getDenominator());
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
