class Darts {
    int score(double xOfDart, double yOfDart) {
        double distance = Math.sqrt(xOfDart * xOfDart + yOfDart * yOfDart);

        if (distance > 10) {
            return 0;
        } else if (distance > 5) {
            return 1;
        } else if (distance > 1) {
            return 5;
        } else {
            return 10;
        }
    }
}
