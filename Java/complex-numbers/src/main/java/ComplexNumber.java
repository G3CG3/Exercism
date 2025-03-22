class ComplexNumber {
    private final double real;
    private final double imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    ComplexNumber add(ComplexNumber other) {
        double realPart = this.real + other.getReal();
        double imaginaryPart = this.imaginary + other.getImaginary();
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber subtract(ComplexNumber other) {
        double realPart = this.real - other.getReal();
        double imaginaryPart = this.imaginary - other.getImaginary();
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber multiply(ComplexNumber other) {
        double firstTerm = this.real * other.getReal() - this.imaginary * other.getImaginary();
        double secondTerm = this.real * other.getImaginary() + this.imaginary * other.getReal();
        return new ComplexNumber(firstTerm, secondTerm);
    }

    ComplexNumber divide(ComplexNumber other) {
        double denominator = other.getReal() * other.getReal() + other.getImaginary() * other.getImaginary();
        double realPart = (this.real * other.getReal() + this.imaginary * other.getImaginary()) / denominator;
        double imaginaryPart = (this.imaginary * other.getReal() - this.real * other.getImaginary()) / denominator;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(this.real, -this.imaginary);
    }

    ComplexNumber exponentialOf() {
        double expReal = Math.exp(real);
        double realPart = expReal * Math.cos(imaginary);
        double imaginaryPart = expReal * Math.sin(imaginary);
        return new ComplexNumber(realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        } else {
            return real + " + " + imaginary + "i";
        }
    }
}